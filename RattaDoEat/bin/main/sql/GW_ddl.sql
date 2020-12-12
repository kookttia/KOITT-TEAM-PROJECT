-- DDL 작성자:GW   
-- 수정일 2020.11.02 --어드민 계정 추가

-- DB에 추가하고 홈페이지에서 직접 id@naver.com , 비밀번호: 1234 등 테스터용 유저를 생성해 사용하세요. 
-- 어드민 계정 admin@admin 비밀번호: 1234

---------------------------------DDL -------------------------------------
--DROP
/*

DROP SEQUENCE user_info_SEQ;
DROP SEQUENCE user_roles_SEQ;
DROP TABLE user_info;
DROP TABLE user_roles;
DROP TABLE users;
DROP TABLE persistent_logins;

*/

--유저 정보 테이블 생성
CREATE TABLE user_info
(
    u_idx       NUMBER           NOT NULL, 
    user_id     VARCHAR2(100)    NOT NULL, 
    nickname    VARCHAR2(30)     NULL, 
    name        VARCHAR2(50)     NULL, 
    regdate     DATE             NULL, 
    ip          VARCHAR2(40)     NULL, 
    social      CHAR(1)          NULL, 
    phone       VARCHAR2(13)     NULL, 
    birth       DATE             NULL, 
    address     VARCHAR2(300)    NULL, 
    CONSTRAINT USER_INFO_PK PRIMARY KEY (u_idx)
);
ALTER TABLE user_info
    ADD CONSTRAINT UC_userId UNIQUE (user_id);
    
CREATE SEQUENCE user_info_SEQ
START WITH 1
INCREMENT BY 1;

ALTER TABLE USER_INFO ADD profile_picture VARCHAR(300) DEFAULT '/RattaDoEat/UserProfilePicture/NoPictures.jpg' ;

COMMENT ON TABLE user_info IS 'author : GW';
COMMENT ON COLUMN user_info.u_idx IS '인덱스';
COMMENT ON COLUMN user_info.user_id IS '아이디(이메일)';
COMMENT ON COLUMN user_info.nickname IS '닉네임';
COMMENT ON COLUMN user_info.name IS '이름';
COMMENT ON COLUMN user_info.regdate IS '가입일';
COMMENT ON COLUMN user_info.ip IS '아이피';
COMMENT ON COLUMN user_info.social IS '코드로 구분(naver=N, google=G, kakao=K)';
COMMENT ON COLUMN user_info.phone IS '휴대폰';
COMMENT ON COLUMN user_info.birth IS '생일';
COMMENT ON COLUMN user_info.address IS '집주소';
COMMENT ON COLUMN user_info.profile_picture IS '유저 프로필 사진';

--user roles 테이블 생성
CREATE TABLE user_roles
(
    user_role_id    NUMBER           NOT NULL, 
    username        VARCHAR2(100)    NOT NULL, 
    role            VARCHAR2(45)     NOT NULL, 
    CONSTRAINT USER_ROLES_PK PRIMARY KEY (user_role_id)
);

CREATE SEQUENCE user_roles_SEQ
START WITH 1
INCREMENT BY 1;



-- users 테이블 생성
CREATE TABLE users
(
    username    VARCHAR2(100)    NOT NULL, 
    password    VARCHAR2(100)    NOT NULL, 
    enable      NUMBER           NOT NULL, 
    CONSTRAINT USERS_PK PRIMARY KEY (username)
);

ALTER TABLE user_roles
    ADD CONSTRAINT FK_user_roles_username_users_u FOREIGN KEY (username)
        REFERENCES users (username) on DELETE CASCADE; -- 주의 케스케이드 확인하기
        
-- 로그인 유지 테이블 생성
CREATE TABLE persistent_logins
(
    series       VARCHAR2(64)     NOT NULL, 
    username     VARCHAR2(100)    NOT NULL, 
    token        VARCHAR2(64)     NOT NULL, 
    last_used    TIMESTAMP        NOT NULL, 
    CONSTRAINT PERSISTENT_LOGINS_PK PRIMARY KEY (series)
);
    
    
    --트리거 (미사용)
    /*
CREATE OR REPLACE TRIGGER user_info_AI_TRG
BEFORE INSERT ON user_info 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT user_info_SEQ.NEXTVAL
    INTO :NEW.u_idx
    FROM DUAL;
END;
*/
--DROP TRIGGER user_info_AI_TRG;

/*
CREATE OR REPLACE TRIGGER user_roles_AI_TRG
BEFORE INSERT ON user_roles 
REFERENCING NEW AS NEW FOR EACH ROW 
BEGIN 
    SELECT user_roles_SEQ.NEXTVAL
    INTO :NEW.user_role_id
    FROM DUAL;
END;*/


--DROP TRIGGER user_roles_AI_TRG;


------------------------------  DML  --------------------------------------
-------------------------------------------------------------
-- admin 계정
-- pw : 1234

INSERT INTO user_info(u_idx, user_id, name)
VALUES (user_info_seq.nextval, 'admin@admin','관리자');

INSERT INTO users(username,password,enable)
VALUES ('admin@admin','$2a$04$8WCdTXymFDJFeBJAVTmqZ.JySf4WcONUkNt3KOnbq5eWTJyYt595W', 1);

INSERT INTO user_roles (user_role_id, username, role)
VALUES (user_roles_seq.nextval, 'admin@admin', 'ROLE_ADMIN');

SELECT * FROM users WHERE username='admin@admin';

--delete user_info where user_id='admin@admin';
--delete user_roles where username='admin@admin';
--delete users where username='admin@admin';