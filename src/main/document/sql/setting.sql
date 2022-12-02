
--SQLPLUS로 실행해 주세요
CREATE USER Enquete_Master IDENTIFIED BY Survey_2022;
GRANT CONNECT, RESOURCE TO Enquete_Master;

conn Enquete_Master/Survey_2022;

-- 유저
CREATE TABLE AddressTBL (
    UserID VARCHAR2(256) NOT NULL,
    Password VARCHAR2(32) NOT NULL,
    Name VARCHAR2(256) NOT NULL,
    MailAddress VARCHAR2(256) UNIQUE NOT NULL,
    Extend00 VARCHAR2(256),
    Extend01 VARCHAR2(256),
    Extend02 VARCHAR2(256),
    Extend03 VARCHAR2(256),
    Extend04 VARCHAR2(256),
    Extend05 VARCHAR2(256),
    Admin NUMBER(1),
    LastLoginDate DATE,
    RegDate	DATE,
    PRIMARY KEY (UserID)
);
-- 기본 시스템 관리자 등록
INSERT INTO AddressTBL (UserID, Password, Name, MailAddress, Admin, RegDate)
VALUES ('admin', 'pass', '시스템관리자', 'admin@example.com', 0, SYSDATE);
--SELECT * FROM AddressTBL;

-- 앙케이트 그룹
CREATE TABLE EnqueteGroup (
	GroupId NUMBER NOT NULL,
	GroupName VARCHAR2(256) NOT NULL,
	MaxEnquete NUMBER(2) NOT NULL,
	ExpireDate DATE,
	Creater VARCHAR2(256) NOT NULL,
	RegDate DATE,
	PRIMARY KEY (GroupId),
	FOREIGN KEY (Creater) REFERENCES AddressTBL(UserID)
);
CREATE SEQUENCE EnqueteGroup_seq INCREMENT BY 1 START WITH 0 MINVALUE 0;

-- 앙케이트
CREATE TABLE Enquete (
	EnqueteId NUMBER NOT NULL,
	EnqueteName VARCHAR2(256) NOT NULL,
	GroupId NUMBER NOT NULL,
	OpenStatus NUMBER(1) DEFAULT 0,
	BeginDate DATE,
	EndDate DATE,
	jsonData LONG,
	RegDate DATE,
	PRIMARY KEY (EnqueteId),
	FOREIGN KEY (GroupId) REFERENCES EnqueteGroup(GroupId)
);
CREATE SEQUENCE Enquete_seq INCREMENT BY 1 START WITH 0 MINVALUE 0;


-- 앙케이트 투고
CREATE TABLE Answer (
	AnswerId NUMBER NOT NULL,
	EnqueteId NUMBER NOT NULL,
	QuestionId NUMBER NOT NULL,
	AnswerValue NUMBER(1) NOT NULL,
	RegDate DATE,
	PRIMARY KEY (AnswerId, EnqueteId, QuestionId),
	FOREIGN KEY (EnqueteId) REFERENCES Enquete(EnqueteId)
);
CREATE SEQUENCE Answer_seq INCREMENT BY 1 START WITH 0 MINVALUE 0;

-- test code
--SELECT * FROM (SELECT QuestionId, AnswerValue FROM Answer WHERE EnqueteId = 1) PIVOT(COUNT(AnswerValue) FOR AnswerValue IN (1, 2, 3, 4)) ORDER BY QuestionId;
--SELECT COUNT(QuestionId) FROM (SELECT QuestionId FROM Answer WHERE EnqueteId = 1 GROUP BY QuestionId) T;
--SELECT Extend00, Extend01 FROM AddressTBL GROUP BY Extend00, Extend01 ORDER BY Extend00, Extend01;
--SELECT ROW_NUMBER() OVER (ORDER BY UserId) pin, UserId, Name, Admin, LastLoginDate FROM AddressTBL WHERE name = '%관리자'