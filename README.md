# capstone_be

api 실행시 스웨거 주소

http://localhost:8080/swagger-ui/index.html

http://localhost:8090/swagger-ui/index.html

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
여기서 필요없는 정보는 NULL로 보내도 됨

### project-edit-controller
```
프로젝트 구인글을 수정하는 기능
projectTitle, description, ProjectStatus, status, recruitmentCount를 받아와 해당 projectId에 해당하는 5가지 값을 수정
추가로 techNames들을 받아와 생성,삭제를 함
기존 정보를 가져오는 방법은 project-more-information-controller 사용
```

### user-information-controller /user_information
```
유저의 정보를 가져오는 기능
userId 값을 입력하면 그 아이디에 해당하는 테이블의 모든 정보를 가져옴
```

### user-information-controller /user_information_update
```
유저의 정보 업데이트하는 기능
유저의 userId에 해당하는 name, email, gitAddress를 수정
```

### likes-controller /check_like
```
기존에 좋아요를 눌렀나 체크하는 기능
유저의 userId와 프로젝트 id를 입력하여 확인('already liked this project' 출력시 이미 좋아요 누른 것임)
```

### likes-controller /toggle_like
```
좋아요 유무 체크 후 체크되어 있다면 좋아요 취소 기능을 체크되어 있지 않으면 좋아요 누르는 기능
해당 유저의 아이디와 프로젝트 아이디 를 가져와 좋아요 유무 체크
execute를 반복해서 누르면  like removed와 Like added가 반복되어 토글처럼 구현
프로젝트 생성자의 아이디를 가져와 그 아이디에 대해서 좋아요 입력시 포인트 1점 추가, 좋아요 취소 시에는 포인트 1점 감소
```

### project-more-information-controller
```
프로젝트 구인글에 자세한 정보를 가져오는 기능
projectId에 대한 값을 입력하면 모든 값을 가져옴
그리고 projectId에 대한 techNames 들도 가져옴
가져와서 projectId와 techNames에 대한 구조체로 반환함
```

### sign-up-controller
```
회원 가입 기능
userId, password, name, nickname, email, gitAddress를 입력받으면 db에 추가
```

### search-project-controller
```
프로젝트 구인글 검색 기능
사용자가 어떤 text를 입력하면 projectTitle을 검색해 그 중 해당하는 검색어가 있는 프로젝트 구인글들의 정보를 가져옴
가져온 projectId에 대한 techNames도 가져옴
최종적으로 projectId와 techNames에 대한 구조체로 반환함
```

### pass-controller / accept
```
프로젝트 생성자가 신청 리스트 중 1명을 선택해서 합격 시키는 기능
합격 시키면 기존 신청 테이블에서 정보 삭제후 삭제에 성공하면 합격 테이블로 정보 이전, 이때 status는 PENDING(대기중) ->  ACCEPTED(합격)으로 수정됨
userId와 ProjectId 만 있으면 됨
```

### pass-controller / apply_list
```
신청자 리스트 기능
projectId를 입력하면 그 프로젝트에 대한 신청자 리스트가 반환
```

### pass-controller / reject
```
신청자 거부 기능
신청자 리스트 중에 1명을 선택해 거절함
userId와 projectId를 받아와 신청 테이블에서 status를 REJECTED(거부)로 변경함
```

### login-controller
```
로그인 기능
userId 와 passoword를 입력해 둘이 일치하면 로그인 성공을 반환, 일치하지 않으면 로그인 실패
```

### project-generate-controller / gennerate_project
```
프로젝트 구인글 생성 기능
projectTitle, description, userId, recruitmentCount를 입력하면 나머지 값들은 자동으로 생성
-> projectIId 자동 증가 생성, ProjectStatus = 'Ps_pr' (프로젝트 진행중), status = 'S_pr'(구인 중), likes =0, views = 0 , generateData = 현재 시간(api 내부에서 처리)
```

### project-generate-controller / gennerate_project_Tech
```
프로젝트 구인글 생성 시 기술 스택도 같이 생성
#아래는 예시
"projectId": 1,
  "techNames": [
    "java",
    "sql"
  ]
techStack 테이블에 해당하는 기술스택이 생성 후 projectTechMapping 테이블에 저장됨
techStack은 '현재 예시 insert문' 에 예시로 저장되어있는 순서
```

### application-controller / apply
```
사용자가 프로젝트에 구직 신청을 하는 기능
userId와 ProjectId를 입력 시 신청 완료
-> stsus는 PENDING(대기중)으로 자동 생성, applyDate는 api내부에서 처리
```

### application-controller / canncel_apply
```
신청 취소 기능
userId와 ProjectId를 입력 db에서 삭제
```

### application-controller / my_applications
```
내가 신청한 프로젝트 정보 보는 기능
userId를 입력하면 신청 정보 출력
```

### comment-controller /comments/generate
```
댓글 생성 기능
해당하는 projectId와 댓글 생성자 아이디 userId, 댓글 내용 content 를 가져와 DB에 추가, 나머지는 자동생성
-> commentId는 자동 증가 생성, createdAt 생성시간은 api 내부에서 처리
```

### comment-controller /comments/list
```
프로젝트 구인글의 댓글을 가져오는 기능
해당하는 projectId에 달려있는 모든 댓글 테이블의 모든 정보를 가져옴
```

### comment-controller /comments/edit
```
프로젝트 구인글의 댓글을 수정하는 기능
```

### comment-controller /comments/delete
```
프로젝트 구인글의 댓글을 삭제하는 기능
```

### project-list-controller
```
모든 프로젝트 구인글 정보를 가져오는 기능
프로젝트 구인 테이블의 모든 리스트들의 정보를 가져옴
+ 기술 스택 정보로 가져옴
```

### point-ranking-controller
```
포인트 랭킹 기능
모든 유저를 포인트 순대로 나열후 userId, name, Point 3가지 정보를 가져옴
```

### nick-name-duplicate-cheack
```
닉네임 중복 체크 기능
닉네임 입력시 중복 확인을 함
```

### id-duplicate-check
```
아이디 중복 체크 기능
아이디를 입력하면 그 아이디가 사용 중인지 사용가능한지 반환
```

### project-delete-controller
```
프로젝트 구인글 삭제 기능
projectId를 입력받아 그 projectId에 해당하는 모든 정보를 삭제함
```

### 그외...
```
hello-controller
db-connect
이 2가지 컨트롤러는 테스트 용
```


진행사항
==================
spring boot 실행 및 swagger 등록 성공

230912 로그인 및 회원가입 기능 구현

230914 구인글 올리기 기능 구현, db 자동 생성 기능, 아이디 중복체크 기능 수정 및 get방식으로 수정

230917 구인글 목록 기능 추가

230919 아이디 정보 가져오기 및 수정 기능, 프로젝트 검색 기능, 프로젝트 세부 설명 보는 기능 추가

231006 포인트 추가 기능 추가 (

게시물 작성 (10점 증가)
게시물 조회수 (1점 증가)
좋아요(1점 증가)
댓글(1점 증가)
프로젝트 완료(100점 증가)

)



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
    projectId int PRIMARY KEY,                 /* 프로젝트 고유 아이디*/
    projectTitle VARCHAR(100),                 /* 프로젝트 제목*/
    description TEXT,                          /* 프로젝트 설명*/
    userId VARCHAR(50),                        /* 프로젝트 생성자 아이디*/
    projectStatus VARCHAR(50) DEFAULT 'Ps_pr', /* Ps_pr은 프로젝트 진행중,Ps_co는 프로젝트 완료*/
    status VARCHAR(50) DEFAULT 'S_pr',         /*S_pr은 구인 진행중, S_co는 구인 완료*/
    recruitmentCount int,                      /* 구인 인원 수*/
    generateDate varchar(50),                  /* 프로젝트 생성 날짜*/
    likes int DEFAULT 0,                       /* 좋아요 수 (생성시 0개)*/
    views int DEFAULT 0                        /* 조회수 (생성시 0회)*/
);

CREATE TABLE techStack (
    techId int PRIMARY KEY AUTO_INCREMENT,      /* 기술 번호 (자동증가)*/
    techName VARCHAR(100) NOT NULL              /* 기술 이름 */
);

CREATE TABLE projectTechMapping (
    projectId int,                      /* 프로젝트 id */
    techName varchar(100) NOT NULL      /* 기술 이름*/
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
    status VARCHAR(50) DEFAULT 'PENDING',             /* PENDING: 대기중, REJECTED: 거절됨 */
    applyDate varchar(50)                             /* 신청한 날짜*/
);

/* 합격 테이블 */
CREATE TABLE pass (
    userId VARCHAR(50) NOT NULL,                       /* 합격한 유저 id */
    projectId INT NOT NULL,                            /* 합격한 프로젝트 id */
    status VARCHAR(50) DEFAULT 'ACCEPTED',             /* ACCEPTED: 수락됨 */
    passDate varchar(50)                               /* 합격한 날짜 */
);

```

현재 예시 insert문
====

```
#여기다가 defualt로 넣을 데이터 집어넣으면 됨
INSERT INTO users(userId, password, name, nickname, email, gitAddress, point)
VALUES
('alice123', 'password123', 'Alice Kim', 'alice', 'alice.kim@email.com', 'https://github.com/alicekim', 100),
('bob456', 'password456', 'Bob Lee', 'bob', 'bob.lee@email.com', 'https://github.com/boblee', 100),
('charlie789', 'password789', 'Charlie Park', 'charlie', 'charlie.park@email.com', 'https://github.com/charliepark', 0),
('david101', 'password101', 'David Cho', 'david', 'david.cho@email.com', 'https://github.com/davidcho', 0);

INSERT INTO projectGenerate(projectId, projectTitle, description, userId, projectStatus, status, recruitmentCount, generateDate, likes, views)
VALUES
(1,'테스트 프로젝트','html 및 text', 'alice123', 'Ps_pr', 'S_pr', 2, '2023-09-14 10:34', 0, 0),
(2,'테스트 프로젝트2','밥이만든 테스트 프로젝트 ', 'bob456', 'Ps_pr', 'S_pr', 5, '2023-09-14 10:34', 0, 0);

INSERT INTO techStack(techName) /* 아래는 예시 데이터 셋 1번부터 시작*/
VALUES
('Java'),
('Python'),
('JavaScript'),
('HTML'),
('CSS'),
('SQL'),
('C'),
('C++'),
('C#');

INSERT INTO projectTechMapping(projectId, techName)
VALUES
(1,'Java'),
(1,'CSS'),
(1,'SQL'),
(2,'SQL');
```
