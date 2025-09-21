-- 사용자
CREATE TABLE user (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(10) NOT NULL,
	nickname VARCHAR(20) NOT NULL UNIQUE,
	email VARCHAR(40) NOT NULL UNIQUE,
	password VARCHAR(100) NOT NULL,
	phone VARCHAR(15) UNIQUE,
	birth_date DATE NOT NULL,
	address TEXT,
	gender VARCHAR(10) NOT NULL DEFAULT 'UNKNOWN', -- ENUM(남, 여, 선택안함)
	created_at DATETIME(6) NOT NULL,
	updated_at DATETIME(6) NOT NULL,
	status VARCHAR(15),
	inactive_date DATETIME
);

-- 음식 유형
CREATE TABLE food_type (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	food_name VARCHAR(20) NOT NULL -- ENUM(한식, 양식, 중식 ...)
);

-- 사용자-음식 유형 매핑 테이블
CREATE TABLE user_food_preference (
	user_id BIGINT NOT NULL,
	food_type_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, food_type_id),
	FOREIGN KEY (user_id) REFERENCES user(id),
	FOREIGN KEY (food_type_id) REFERENCES food_type(id)
);

-- 지역
CREATE TABLE region (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	region_name VARCHAR(20) NOT NULL UNIQUE
);

-- 가게
CREATE TABLE store (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	region_id BIGINT NOT NULL,
	store_name VARCHAR(20) NOT NULL,
	store_number INT NOT NULL,
	address VARCHAR(255) NOT NULL,
	created_at DATETIME NOT NULL,
	FOREIGN KEY (region_id) REFERENCES region(id)
);

-- 미션
CREATE TABLE mission (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	store_id BIGINT NOT NULL,
	mission_name VARCHAR(40) NOT NULL,
	description TEXT,
	reward_point INT NOT NULL,
	created_at DATETIME NOT NULL,
	FOREIGN KEY (store_id) REFERENCES store(id)
);

-- 사용자-미션 매핑 테이블
CREATE TABLE user_mission (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	user_id BIGINT NOT NULL,
	mission_id BIGINT NOT NULL,
	is_completed BOOLEAN NOT NULL DEFAULT FALSE,
	completed_at DATETIME,
    UNIQUE (user_id, mission_id),
	FOREIGN KEY (user_id) REFERENCES user(id),
	FOREIGN KEY (mission_id) REFERENCES mission(id)
);

-- 리뷰
CREATE TABLE review (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	store_id BIGINT NOT NULL,
	user_mission_id BIGINT NOT NULL,
	comment TEXT NOT NULL,
	rating INT NOT NULL,
	created_at DATETIME NOT NULL,
	FOREIGN KEY (store_id) REFERENCES store(id),
	FOREIGN KEY (user_mission_id) REFERENCES user_mission(id)
);

-- 리뷰 사진
CREATE TABLE review_photo (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	review_id BIGINT NOT NULL,
	photo_url VARCHAR(255) NOT NULL,
	created_at DATETIME NOT NULL,
	FOREIGN KEY (review_id) REFERENCES review(id)
);