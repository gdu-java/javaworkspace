DROP TABLE TEST;
CREATE TABLE TEST(
    TNO NUMBER,
    TNAME VARCHAR2(20),
    TDATE DATE
);

DROP SEQUENCE SEQ_TESTNO;
CREATE SEQUENCE SEQ_TESTNO NOCACHE;

INSERT INTO TEST VALUES(SEQ_TESTNO.NEXTVAL, '테스트1', '2022-04-01');
INSERT INTO TEST VALUES(SEQ_TESTNO.NEXTVAL, '테스트2', '2022-04-12');
INSERT INTO TEST VALUES(SEQ_TESTNO.NEXTVAL, '테스트3', '2022-04-23');



DROP TABLE MEMBER;
CREATE TABLE MEMBER(
    USER_NO NUMBER PRIMARY KEY,                  -- 회원번호
    USER_ID VARCHAR2(15) NOT NULL UNIQUE,        -- 회원아이디
    USER_PWD VARCHAR2(15) NOT NULL,              -- 회원비번
    USER_NAME VARCHAR2(20) NOT NULL,             -- 회원명
    GENDER CHAR(1) CHECK(GENDER IN ('M', 'F')), -- 성별
    AGE NUMBER,                                 -- 나이
    EMAIL VARCHAR2(30),                         -- 이메일
    PHONE CHAR(11),                             -- 전화번호
    ADDRESS VARCHAR2(100),                      -- 주소
    HOBBY VARCHAR2(50),                         -- 취미
    ENROLL_DATE DATE DEFAULT SYSDATE NOT NULL    -- 회원가입일
);

DROP SEQUENCE SEQ_USERNO;
CREATE SEQUENCE SEQ_USERNO NOCACHE;

INSERT INTO MEMBER
VALUES(SEQ_USERNO.NEXTVAL, 'admin', '1234', '관리자', 'M', 45, 'admin@iei.or.kr', '01012345555', '서울', null, '2022-04-11');

INSERT INTO MEMBER
VALUES(SEQ_USERNO.NEXTVAL, 'user01', 'pass01', '홍길동', null, 23, 'user01@iei.or.kr', '01022221111', '부산', '등산,영화보기', '2022-04-23');

COMMIT;






