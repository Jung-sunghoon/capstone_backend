DROP DATABASE IF EXISTS capstone;
CREATE DATABASE capstone;

USE capstone;

CREATE TABLE users (
    userId VARCHAR(100) PRIMARY KEY,       /* 유저 아이디 */
    password VARCHAR(100),                 /* 유저 비밀번호 */
    name VARCHAR(50),                      /* 유저 이름 */
    department VARCHAR(100),               /* 유저 학과 */
    studentNumber int,                     /* 유저 학번 */
    nickname VARCHAR(50),                  /* 유저 닉네임 */
    email VARCHAR(100),                    /* 유저 이메일*/
    gitAddress VARCHAR(100),               /* 유저 깃허브 주소*/
    point int DEFAULT 0,                   /* 유저 점수(0점으로 시작)*/
    techStacks VARCHAR(255)                /* 사용자 태크 스택들*/
);

CREATE TABLE projectGenerate (
    projectId int PRIMARY KEY,                 /* 프로젝트 고유 아이디*/
    projectTitle VARCHAR(100),                 /* 프로젝트 제목*/
    description LONGTEXT,                      /* 프로젝트 설명*/
    userId VARCHAR(50),                        /* 프로젝트 생성자 아이디*/
    projectStatus VARCHAR(50),                 /* Ps_pr은 프로젝트 진행중,Ps_co는 프로젝트 완료*/
    status VARCHAR(50) ,                       /*S_pr은 구인 진행중, S_co는 구인 완료*/
    recruitmentCount int,                      /* 구인 인원 수*/
    generateDate varchar(50),                  /* 프로젝트 생성 날짜*/
    likes int DEFAULT 0,                       /* 좋아요 수 (생성시 0개)*/
    views int DEFAULT 0,                       /* 조회수 (생성시 0회)*/
    thumbnail LONGTEXT                         /* 썸네일 이미지 저장경로*/
);


CREATE TABLE techStack (
    techId int PRIMARY KEY AUTO_INCREMENT,      /* 기술 번호 (자동증가)*/
    techName VARCHAR(100) NOT NULL              /* 기술 이름 */
);


CREATE TABLE projectTechMapping (
    projectId int,                      /* 프로젝트 id */
    techId int NOT NULL                 /* 기술 번호*/
);

CREATE TABLE Comments (
    commentId INT AUTO_INCREMENT PRIMARY KEY,    /* 댓글 고유 아이디 */
    projectId INT,                               /* 댓글을 입력한 프로젝트 고유 아이디*/
    userId VARCHAR(100),                         /* 댓글 생성한 유저 아이디*/
    content TEXT,                                /* 댓글 내용*/
    createdAt varchar(50),                       /* 댓글 생성 날짜*/
    updatedAt varchar(50)                        /* 댓글 수정 날짜*/
);

CREATE TABLE likes (
    userId VARCHAR(100) NOT NULL,      /* 좋아요 누른 아이디 */
    projectId INT NOT NULL             /* 좋아요 눌러진 프로젝트 id */
);

/* 신청 테이블 */
CREATE TABLE application (
    userId VARCHAR(50) NOT NULL,                      /* 신청한 유저 id */
    projectId INT NOT NULL,                           /* 신청한 프로젝트 id */
    status VARCHAR(50) DEFAULT 'PENDING',             /* PENDING: 대기중, REJECTED: 거절됨, CANCEL: 취소 */
    applyDate varchar(50)                             /* 신청한 날짜*/
);

/* 합격 테이블 */
CREATE TABLE pass (
    userId VARCHAR(50) NOT NULL,                       /* 합격한 유저 id */
    projectId INT NOT NULL,                            /* 합격한 프로젝트 id */
    status VARCHAR(50) DEFAULT 'ACCEPTED',             /* ACCEPTED: 수락됨 */
    passDate varchar(50)                               /* 합격한 날짜 */
);

CREATE TABLE ItInformation(
    itInfoId INT AUTO_INCREMENT PRIMARY KEY,       /* IT 정보 Id */
    title VARCHAR(100),                            /* IT 정보 제목 */
    description LONGTEXT,                          /* IT 정보 내용 */
    generateDate varchar(50)                       /* IT 정보 생성일 */
);