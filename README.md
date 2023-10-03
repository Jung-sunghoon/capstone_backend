# capstone_be

api 실행시 스웨거 주소

http://localhost:8080/swagger-ui/index.html

설정
========
**intellig IDEA** 사용
***
gradle 말고 **maven** 이용
***
DB는 **MySQL 8.0.33** 버전 이용 및 **MySQL workbench**를 이용

추가로 SQL을 유연하게 이용하기 위해 **Mybatis(마이바티스)** 사용
***
종속성 추가하는 방법(maven)
-----------
아래는 예시
```
<dependency>
			<groupId>com.mysql</groupId>
			<artifactId>mysql-connector-j</artifactId>
</dependency>
```
위의 코드 추가 후

![image](https://github.com/Jung-sunghoon/capstone_be/assets/101784544/2d047862-2c57-4f50-a017-4cbf64fbb62f)

위의 그림처럼 오른쪽 완전 끝부분 m Maven 클릭후 재활용 모양(모든 maven 프로젝트 다시 로드) 버튼 누르면 적용됨(빨간색 글씨 사라짐)

***
back/demo/src/main/resources/application.properties

이 파일에 

spring.datasource.jdbc-url=jdbc:mysql://localhost:3307/capstone 

접속안될 시 3307 -> 3306 으로 변경

***
db관련 추가 사항 230914
-----------------------
application.properties에 아래 코드 추가
```
spring.sql.init.mode=always
spring.sql.init.continue-on-error=true
#spring.sql.init.data-locations=classpath:data-postgresql.sql
spring.sql.init.schema-locations=classpath:schema-postgresql.sql
```
추가시 항상 schema-postgresql.sql문을 서버 시작시 실행함

-> DB를 따로 sql workbench에서 create 해줄 필요가 없어짐 (추가된 데이터 확인은 추가 필요)

main/resurces/schema-postgresql.sql 여기에다가 새로만들 db 테이블 추가

main/resurces/data-postgresql.sql 여기는 테스트용 데이터셋을 넣는 부분(insert into ... Values....), 현재는 주석 처리  



스웨거에서 각 기능에 대한 설명 및 필요 요소들
==================


### project-edit-controller
```
구인글을 수정하는 기능
projectTitle, description, ProjectStatus, status, recruitmentCount를 받아와 해당 projectId에 해당하는 5가지 값을 수정
```




진행사항
==================
spring boot 실행 및 swagger 등록 성공

230912 로그인 및 회원가입 기능 구현

230914 구인글 올리기 기능 구현, db 자동 생성 기능, 아이디 중복체크 기능 수정 및 get방식으로 수정

230917 구인글 목록 기능 추가

230919 아이디 정보 가져오기 및 수정 기능, 프로젝트 검색 기능, 프로젝트 세부 설명 보는 기능 추가




현재 DB 생성문
====

```
DROP DATABASE IF EXISTS capstone;
CREATE DATABASE capstone;

USE capstone;

CREATE TABLE users (
    userId VARCHAR(100) PRIMARY KEY,       /* 유저 아이디 */
    password VARCHAR(100),                 /* 유저 비밀번호 */
    name VARCHAR(50),                      /* 유저 이름 */
    nickname VARCHAR(50),                  /* 유저 닉네임 */
    email VARCHAR(100),                    /* 유저 이메일*/
    gitAddress VARCHAR(100),               /* 유저 깃허브 주소*/
    point int DEFAULT 0                    /* 유저 점수(0점으로 시작)*/
);


CREATE TABLE projectGenerate (
    projectId int PRIMARY KEY,           /* 프로젝트 고유 아이디*/
    projectTitle VARCHAR(100),           /* 프로젝트 제목*/
    description TEXT,                    /* 프로젝트 설명*/
    userId VARCHAR(50),                  /* 프로젝트 생성자 아이디*/
    ProjectStatus int DEFAULT 0,         /* 프로젝트 진행 상태 0 = 진행중, 1 = 완료*/
    status int DEFAULT 0,                /* 프로젝트 구인 상태 0 = 진행중, 1 = 완료*/
    recruitmentCount int,                /* 구인 인원 수*/
    generateDate varchar(50),            /* 프로젝트 생성 날짜*/
    likes int DEFAULT 0,                 /* 좋아요 수 (생성시 0개)*/
    views int DEFAULT 0                  /* 조회수 (생성시 0회)*/ 
);


CREATE TABLE Comments (
    commentId INT AUTO_INCREMENT PRIMARY KEY,    /* 댓글 고유 아이디*/
    projectId INT,                               /* 댓글을 입력한 프로젝트 고유 아이디*/
    userId VARCHAR(100),                         /* 댓글 생성한 유저 아이디*/
    content TEXT,                                /* 댓글 내용*/
    createdAt varchar(50),                       /* 댓글 생성 날짜*/
    updatedAt varchar(50)                        /* 댓글 수정 날짜*/
);

```
