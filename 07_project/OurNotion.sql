-- 회원 테이블 (T_USER)
CREATE TABLE T_USER (
    UserSeq int PRIMARY KEY,
    ID varchar(12),
    PWD varchar(64),
    gender int,
    name varchar(64),
    birth varchar(8),
    telNum varchar(11),
    DeptSeq int,
    email varchar(64),
    IsSocial int,
    profile varchar2(4000),
    LastDateTime DATE
);

-- 시퀀스 생성
CREATE SEQUENCE user_seq
START WITH 1
INCREMENT BY 1
NOCACHE;

-- 트리거 생성
CREATE OR REPLACE TRIGGER trg_user_seq
BEFORE INSERT ON T_USER
FOR EACH ROW
BEGIN
    :NEW.UserSeq := user_seq.NEXTVAL;
END;

-- 부서 테이블 (T_Dept)
CREATE TABLE T_Dept (
    DeptSeq int PRIMARY KEY,
    DeptName varchar(10),
    LastDateTime DATE
);

-- 시퀀스 생성
CREATE SEQUENCE dept_seq
START WITH 1
INCREMENT BY 1
NOCACHE;

-- 트리거 생성
CREATE OR REPLACE TRIGGER trg_dept_seq
BEFORE INSERT ON T_Dept
FOR EACH ROW
BEGIN
    :NEW.DeptSeq := dept_seq.NEXTVAL;
END;

-- 소셜 테이블 (T_Social)
CREATE TABLE T_Social (
    UserSeq int PRIMARY KEY,
    SocialName varchar(10),
    SocialCode varchar(64),
    LastDateTime DATE
);


-- 캘린더 테이블 (T_Calender)
CREATE TABLE T_Calender (
    UserSeq int,
    CalSeq int,
    CalName varchar(30),
    CallInfo varchar(150),
    enddatetime DATE,
    deptseq int,
    colorseq int,
    categoryseq int,
    StartDateTime DATE,
    LastDateTime DATE,
    PRIMARY KEY (UserSeq, CalSeq)  -- 복합 기본 키 설정
);


-- 시퀀스 생성
CREATE SEQUENCE calender_seq
START WITH 1
INCREMENT BY 1
NOCACHE;

-- 트리거 생성
CREATE OR REPLACE TRIGGER trg_calender_seq
BEFORE INSERT ON T_Calender
FOR EACH ROW
BEGIN
    :NEW.CalSeq := calender_seq.NEXTVAL;
END;

-- 색상 테이블 (T_CalColor)
CREATE TABLE T_CalColor (
    UserSeq int PRIMARY KEY,
    ColorValue varchar(10),
    LastDateTime DATE
);

-- 일정 카테고리 테이블 (T_Category)
CREATE TABLE T_Category (
    CategoryCode int PRIMARY KEY,
    CategoryName varchar(10),
    LastDateTime DATE
);

-- 시퀀스 생성
CREATE SEQUENCE category_seq
START WITH 1
INCREMENT BY 1
NOCACHE;

-- 트리거 생성
CREATE OR REPLACE TRIGGER trg_category_seq
BEFORE INSERT ON T_Category
FOR EACH ROW
BEGIN
    :NEW.CategoryCode := category_seq.NEXTVAL;
END;

-- 가계부 테이블 (T_Accountbook)
CREATE TABLE T_Accountbook (
    UserSeq int PRIMARY KEY,
    Accountseq int,
    info varchar(10),
    amount DECIMAL(38,127),
    category varchar(10),
    memo varchar(100),
    AccountDate DATE,
    LastDateTime DATE
);


-- 채팅 리스트 테이블 (T_ChatList)
CREATE TABLE T_ChatList (
    UUID varchar2(4000) PRIMARY KEY,
    UserSeq int,
    ChatroomName varchar(20),
    makeChatDate DATE,
    ChatUserInfo varchar2(4000),
    LastDateTime DATE
);

-- 채팅 테이블 (T_Chat)
CREATE TABLE T_Chat (
    UUID varchar2(4000) PRIMARY KEY,
    UserSeq int,
    Message varchar2(4000),
    SendTime DATE
);

-- 게시판 테이블 (T_Board)
CREATE TABLE T_Board (
    UserSeq int,
    BoardSeq int,
    BoardName varchar(30),
    FirstDateTime DATE,
    AttachedFile varchar2(4000),
    LastDateTime DATE,
    PRIMARY KEY(UserSeq, BoardSeq)
);

-- 시퀀스 생성
CREATE SEQUENCE board_seq
START WITH 1
INCREMENT BY 1
NOCACHE;

-- 트리거 생성
CREATE OR REPLACE TRIGGER trg_board_seq
BEFORE INSERT ON T_Board
FOR EACH ROW
BEGIN
    :NEW.BoardSeq := board_seq.NEXTVAL;
END;
