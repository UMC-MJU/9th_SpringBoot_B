-- 리뷰 작성
INSERT INTO review (store_id, user_mission_id, comment, rating, created_at)
SELECT m.store_id, um.id, '리뷰 내용', 5, NOW()
FROM user_mission um
JOIN mission m ON um.mission_id = m.id -- mission 테이블과 조인하여 store_id를 가져옴
WHERE um.user_id = ? AND m.mission_id = ? AND is_completed = TRUE;

-- 마이 페이지

-- 이름, 이메일, 전화번호
SELECT nickname, email, COALEASE(NULLIF(phone, ''), '미인증') AS phone -- 전화번호가 비어있으면 미인증으로 표시
FROM user
WHERE id = ?;

-- 포인트 조회
SELECT COALESCE(SUM(point), 0) FROM point WHERE user_id = ?; -- 포인트가 없으면 0을 반환

-- 작성한 리뷰 확인
SELECT r.id, s.store_name, m.mission_name, r.comment, r.rating, r.created_at
FROM review r
JOIN user_mission um ON r.user_mission_id = um.id -- 해당 리뷰를 남긴 사용자의 미션 확인
JOIN mission m ON um.mission_id = m.id -- 어떤 미션에 대한 리뷰인지 확인
JOIN store s ON m.store_id = s.id -- 어떤 가게에 대한 리뷰인지 확인
WHERE um.user_id = ?
ORDER BY r.created_at DESC; -- 최신 리뷰부터 정렬

-- 내가 진행중, 진행 완료한 미션 모아서 보는 쿼리(페이징 포함)
SELECT um.id, m.reward_point, s.store_name, m.mission_name,
CASE
	WHEN um.is_completed = TRUE THEN '성공'
    ELSE '진행중'
    END AS mission_status
FROM user_mission um
JOIN mission m ON um.mission_id = m.id -- 해당 미션 정보 가져오기
JOIN store s ON m.store_id = s.id -- 어느 가게에서 진행되는 미션인지 확인
WHERE um.user_id = ?
ORDER BY um.completed_at DESC
LIMIT ? OFFSET ?;

-- 홈 화면 쿼리(현재 선택 된 지역에서 도전이 가능한 미션 목록, 페이징 포함)

-- 미션 완료 목록
SELECT COALESCE(COUNT(CASE WHEN um.is_completed = TRUE THEN um.mission_id END), 0) AS completed_missions, 10 AS mission_goal
FROM user u
JOIN user_mission um ON u.id = um.user_id
JOIN mission m ON um.mission_id = m.id
JOIN store s ON m.store_id = s.id
JOIN region r ON s.region_id = r.id
WHERE u.id = ? AND r.id = ?
GROUP BY u.id, r.id;

-- 도전 가능한 미션 목록
SELECT m.id, s.store_name, m.mission_name, m.description, m.reward_point,
DATEDIFF(m.deadline, NOW()) AS days_left -- 마감일 표시
FROM mission m
-- 선택된 지역에 속한 가게의 미션만 가져오기
JOIN store s ON m.store_id = s.id
JOIN region r ON r.id = s.region_id
LEFT JOIN user_mission um ON m.id = um.mission_id AND um.user_id = ?
WHERE r.id = ? -- 현재 선택된 지역
AND m.deadline >= NOW() -- 기한이 지나지 않은 미션만 표시
AND um.is_completed = FALSE -- 아직 도전 가능한 미션만 표시
ORDER BY days_left
LIMIT ? OFFSET ?;