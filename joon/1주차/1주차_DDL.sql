CREATE TABLE user (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
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

CREATE TABLE food_type (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	food_name VARCHAR(20) NOT NULL -- ENUM(한식, 양식, 중식 ...)
);

CREATE TABLE user_food_preference (
    user_id BIGINT NOT NULL,
    food_type_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, food_type_id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (food_type_id) REFERENCES food_type(id)
);

CREATE TABLE term (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100) NOT NULL, -- ENUM(서비스 이용 약관, 개인정보 처리 방침)
    is_required BOOLEAN NOT NULL, -- 필수 여부(필수: TRUE, 선택: FALSE)
    created_at DATETIME(6) NOT NULL
);

CREATE TABLE user_term (
	user_id BIGINT NOT NULL,
    term_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, term_id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (term_id) REFERENCES term(id)
);

CREATE TABLE region (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    region_name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE point (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    region_id BIGINT NOT NULL,
    point INT NOT NULL,
    reason VARCHAR(100) NOT NULL, -- 포인트 지급 사유
    created_at DATETIME(6) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (region_id) REFERENCES region(id),
    INDEX idx_user_region (user_id, region_id)
);

CREATE TABLE store (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    region_id BIGINT NOT NULL,
    store_name VARCHAR(20) NOT NULL,
    store_number INT NOT NULL,
    address VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    UNIQUE (region_id, store_name),
    FOREIGN KEY (region_id) REFERENCES region(id)
);

CREATE TABLE mission (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    store_id BIGINT NOT NULL,
    mission_name VARCHAR(40) NOT NULL UNIQUE,
    description TEXT NOT NULL,
    reward_point INT NOT NULL DEFAULT 0,
    deadline DATETIME NOT NULL,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (store_id) REFERENCES store(id),
    INDEX idx_deadline (deadline)
);

CREATE TABLE user_mission (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    mission_id BIGINT NOT NULL,
    is_completed BOOLEAN DEFAULT FALSE,
    completed_at DATETIME,
    UNIQUE (user_id, mission_id),
    FOREIGN KEY (user_id) REFERENCES user(id),
    FOREIGN KEY (mission_id) REFERENCES mission(id),
    INDEX idx_user_mission (user_id, mission_id)
);

CREATE TABLE review (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    store_id BIGINT NOT NULL,
    user_mission_id BIGINT NOT NULL,
    comment TEXT NOT NULL,
    rating INT NOT NULL,
    created_at DATETIME,
    FOREIGN KEY (store_id) REFERENCES store(id),
    FOREIGN KEY (user_mission_id) REFERENCES user_mission(id)
);

CREATE TABLE review_photo (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    review_id BIGINT NOT NULL,
    photo_url VARCHAR(255) NOT NULL,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (review_id) REFERENCES review(id)
);

CREATE TABLE reply (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
    review_id BIGINT NOT NULL,
    content TEXT NOT NULL,
    created_at DATETIME NOT NULL,
    FOREIGN KEY (review_id) REFERENCES review(id)
);