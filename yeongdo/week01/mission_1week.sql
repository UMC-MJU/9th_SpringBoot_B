-- 리뷰 작성하는 쿼리
INSERT INTO review (assignment_id, title, content, rating)
SELECT ma.id, ?, ?, ?                -- title / content / RATING
FROM mission_assignment ma
WHERE ma.id = ?  -- assignment_id       -- 사용자가 리뷰를 남기려고 클릭한 그 미션에 대해서 바인딩이 됨!
AND ma.member_id = ?
AND ma.status ='COMPLETED'
AND NOT EXISTS (
   SELECT 1 FROM review r 
   WHERE r.assignment_id = ma.id 
);

  ----- 마이페이지 화면 쿼리----------------------------------------------------------------------------------

-- 마이페이지 맨 위에 (이름, 이메일, 전화번호)
SELECT m.id, m.name, m.email, m.phone_number
FROM member m
WHERE m.id = ?; -- member_id 바인딩

-- 내 포인트
-- 신규회원(아무 포인트도 적립하거나 차감한 적이 없는 회원은 기록 자체가 없어서 NULL이 리턴됨
-- 그런 경우를 방지해서 0이 출력되도록 COALESCE
SELECT COALESCE(SUM(pl.delta), 0) 
FROM point_ledger pl
WHERE pl.member_id = ?; -- member_id

-- ----내가 진행중, 진행 완료한 미션 모아서 보는 쿼리(페이징 포함) -----------------------------------------------
SELECT 
t.assignment_id,
t.title,
t.store_name,
t.base_reward,
t.sort_at   -- 다음 커서에서 사용하기 위해 던져줌
FROM(
SELECT ma.id AS assignment_id, m.title, s.name AS store_name, m.base_reward,
   CASE
	WHEN ma.completed_at IS NOT NULL THEN ma.completed_at
	ELSE COALESCE(ma.started_at, ma.assigned_at)
	END AS sort_at -- 완료되지 않았다면 종료시각, 완료되었다면 시작시간이나 할당시간(먼저 null이 아닌 거)
FROM mission_assignment ma
JOIN mission m ON ma.mission_id = m.id
JOIN store s ON m.store_id = s.id
LEFT OUTER JOIN review r ON r.assignment_id = ma.id
WHERE ma.member_id = ?
AND ma.status IN ('IN_PROGRESS', 'COMPLETED') 
) AS t
-- 더 과거인 것 먼저(커서 뒤쪽)
WHERE (t.sort_at < ? OR (sort_at = ? AND t.assignmend_id < ?)) 
ORDER BY t.sort_at DESC, t.assignment_id DESC
LIMIT ?;

 -- 홈 화면 쿼리  ------------------------------------------------------------------------------------------------

-- 홈 화면 윗부분 쿼리 (미션 ?/? 개 수행..)
SELECT COALESCE(mrp.completed_count, 0), 10 -- 다른 지역으로 등록할 수도 있으므로 방지
FROM member m
LEFT OUTER JOIN member_region_progress mrp -- 회원이 바인딩된 지역에서 진행한 기록이 없을 수도 있으므로 
ON m.id = mrp.member_id AND mrp.region_id = ? 
WHERE m.id = ?;


-- MY MISSION 부분 쿼리
SELECT 
t.mission_id, t.title, t.store_name, t.description, t.base_reward, 
CASE 
	WHEN t.ends_at IS NULL THEN NULL
    ELSE GREATEST(DATEDIFF(t.emds_at, NOW(), 0) -- 마감일이 지났으면 음수가 되므로 최저 0으로 보정
END AS days_left,
t.sort_at
FROM (
   SELECT m.id AS mission_id, s.name AS store_name, m.title, m.description, m.ends_at, m.base_reward,
    COALESCE(m.starts_at, m.created_at) AS sort_at -- 시작되지 않는 미션이 null이 되면 페이징 대상에서 빠져 나가므로 
    FROM mission m JOIN store s ON s.id = m.store_id
    WHERE m.region_id = ? -- 선택된 지역에서만
    AND (m.starts_at IS NULL OR m.starts_at <= NOW()) -- 이미 시작 or 언제든 시작
    -- 끝내지 않았거나, 아직 진행중인 것 
    AND (m.ends_at IS NULL OR m.ends_at > NOW()) -- 아직 안 끝남
    AND NOT EXISTS (  -- 이미 받았던 미션은 빼기
    SELECT 1
    FROM mission_assignment ma
    WHERE ma.mission_id = m.id AND
    ma.member_id = ?
) t
WHERE (t.sort_at < ? OR (t.sort_at = ? AND t.mission_id < ?)) 
-- 최신을 먼저 정렬하므로, 다음 페이지는 더 예전 미션을 가리켜야 함
ORDER BY t.sort_at DESC, t.mission_id DESC -- 시간이 같으면 ID 내림차순으로
LIMIT ?;
