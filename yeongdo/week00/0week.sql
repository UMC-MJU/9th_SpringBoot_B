USE umc_0week;

CREATE TABLE member (
  id BIGINT PRIMARY KEY,
  name VARCHAR(50),
  addr1 VARCHAR(100),
  addr2 VARCHAR(100),
  status VARCHAR(50),
  inactive_date DATE,
  gender VARCHAR(20) NOT NULL DEFAULT 'UNKNOWN', -- 구현 시 ENUM으로 MALE, FEMALE 설정
  birth_date DATE,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  CHECK (status IN('ACTIVE', 'INACTIVE'))
);

CREATE TABLE oauth_account (
  member_id BIGINT NOT NULL PRIMARY KEY,
  provider VARCHAR(50) NOT NULL, -- 구현 시 ENUM으로 KAKAO, NAVER, APPLE, GOOGLE 설정
  provider_user_id VARCHAR(191) NOT NULL, -- 소셜 제공자에서 부여한 사용자 id
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  UNIQUE KEY uq_oauth_provider_uid (provider, provider_user_id), -- 카카오 로그인은 한 계정에만 가능..
  FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE,
  CHECK (provider IN ('KAKAO', 'NAVER', 'APPLE', 'GOOGLE'))
); 

CREATE TABLE onboarding_state (
  member_id BIGINT PRIMARY KEY,
  stage VARCHAR(100) NOT NULL, -- ENUM 'SIGNED_UP','CONSENTED','PROFILE_DONE','PREFERENCE_DONE','DONE'
  last_step VARCHAR(50) NULL, -- 이전 단계 저장
  updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  FOREIGN KEY (member_id) REFERENCES member(id) ON DELETE CASCADE,
  CHECK (stage IN ('SIGNED_UP','CONSENTED','PROFILE_DONE','PREFERENCE_DONE','DONE'))
); 

CREATE TABLE region (
  id   BIGINT PRIMARY KEY,
  code VARCHAR(50)  NOT NULL, -- 내부 식별용
  name VARCHAR(100) NOT NULL, -- 보여지는 이름
  UNIQUE KEY uq_region_code (code)
);

CREATE TABLE store (
  id BIGINT PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  addr1 VARCHAR(100),
  addr2 VARCHAR(100),
  owner_secret VARCHAR(20), -- 사장님 구분 번호
  region_id BIGINT NOT NULL,
  FOREIGN KEY (region_id) REFERENCES region(id),
  INDEX (region_id)
); 

CREATE TABLE mission (
  id BIGINT PRIMARY KEY,
  store_id  BIGINT NOT NULL,
  region_id BIGINT NOT NULL,                       
  mission_type VARCHAR(20) NOT NULL, -- 지금은 visit 밖에 없는데, 추후 확장 가능성 고려
  title VARCHAR(120) NOT NULL,
  description TEXT,
  starts_at DATETIME NULL,
  ends_at   DATETIME NULL,
  base_reward INT NOT NULL DEFAULT 0,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  FOREIGN KEY (store_id)  REFERENCES store(id),
  FOREIGN KEY (region_id) REFERENCES region(id),
  INDEX (region_id, mission_type, starts_at),
  INDEX (store_id),
  CHECK (mission_type IN ('VISIT')) -- 확장 시 여기 추가
); 

CREATE TABLE mission_visit (
  id BIGINT PRIMARY KEY,
  min_spend INT NOT NULL DEFAULT 0,
  require_owner_code BOOLEAN NOT NULL DEFAULT FALSE,
  require_receipt    BOOLEAN NOT NULL DEFAULT FALSE,
  FOREIGN KEY (id) REFERENCES mission(id) ON DELETE CASCADE
); 

CREATE TABLE mission_assignment (
  id BIGINT PRIMARY KEY,
  member_id  BIGINT NOT NULL,
  mission_id BIGINT NOT NULL,
  status VARCHAR(50) NOT NULL, -- ENUM으로 'ASSIGNED','IN_PROGRESS','COMPLETED'구현
  assigned_at  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  started_at   DATETIME NULL,
  completed_at DATETIME NULL,
  UNIQUE KEY uq_member_mission (member_id, mission_id),   -- 중복 배정 방지
  FOREIGN KEY (member_id)  REFERENCES member(id)  ON DELETE CASCADE,
  FOREIGN KEY (mission_id) REFERENCES mission(id) ON DELETE CASCADE,
  INDEX (member_id, status, assigned_at),
  INDEX (mission_id),
  CHECK (status IN ('ASSIGNED','IN_PROGRESS','COMPLETED'))
); 

-- 약관 -
CREATE TABLE consent_document ( -- 약관 마스터
  id BIGINT PRIMARY KEY,
  code    VARCHAR(50) NOT NULL, -- TERMS, PRIVACY..
  version VARCHAR(20) NOT NULL,  -- v1, v2 등..
  required BOOLEAN NOT NULL DEFAULT TRUE, -- 필수 여부
  published_at DATETIME(6) NOT NULL, -- ms 정밀도
  UNIQUE KEY uq_code_version (code, version)
);

-- 회원_약관 테이블(누가 어떤 약관에 동의를 했나..)
CREATE TABLE member_consent (
  member_id   BIGINT NOT NULL,
  document_id BIGINT NOT NULL,
  agreed_at   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (member_id, document_id),
  FOREIGN KEY (member_id)   REFERENCES member(id) ON DELETE CASCADE,
  FOREIGN KEY (document_id) REFERENCES consent_document(id)
); 

CREATE TABLE food_category (
  id BIGINT PRIMARY KEY,
  code VARCHAR(50)  NOT NULL, -- 내부 식별용 (KOREAN, JAPANESE..) 
  name VARCHAR(50)  NOT NULL,  -- 보이는 이름 (한식, 일식..)
  UNIQUE KEY uq_food_code (code)
); 

-- member <-> food_category가 M:N 관계라서 연결 테이블로 이어줌
CREATE TABLE member_food_preference (
  id BIGINT PRIMARY KEY,
  member_id   BIGINT NOT NULL,
  category_id BIGINT NOT NULL,
  selected_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE KEY uq_member_category (member_id, category_id), -- 중복 선택 방지
  FOREIGN KEY (member_id)   REFERENCES member(id) ON DELETE CASCADE,
  FOREIGN KEY (category_id) REFERENCES food_category(id) ON DELETE RESTRICT,
  INDEX (member_id)
); 

-- 포인트(원장)
CREATE TABLE point_ledger (       
  id BIGINT PRIMARY KEY,
  member_id BIGINT NOT NULL,
  delta INT NOT NULL,  -- +적립 / -차감
  reason VARCHAR(100) NOT NULL, -- ENUM으로 MISSION_REWARD, REGION_BONUS
  region_id BIGINT NULL,
  assignment_id BIGINT NULL,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  FOREIGN KEY (member_id)   REFERENCES member(id) ON DELETE CASCADE,
  FOREIGN KEY (region_id)   REFERENCES region(id),
  FOREIGN KEY (assignment_id) REFERENCES mission_assignment(id),
  INDEX (member_id, created_at),
  CHECK (reason IN ('MISSION_REWARD','REGION_BONUS')),
  CHECK (delta <> 0) -- 변화량은 0이 될 수 없음
); 

--  지역별 진행현황
CREATE TABLE member_region_progress (
  member_id BIGINT NOT NULL,
  region_id BIGINT NOT NULL,
  completed_count INT NOT NULL DEFAULT 0,
  last_completed_at DATETIME NULL,
  PRIMARY KEY (member_id, region_id), -- 한 멤버의 지역별 진행 현황은 하나로 묶어서 중복 방지
  FOREIGN KEY (member_id) REFERENCES member(id),
  FOREIGN KEY (region_id) REFERENCES region(id)
);
