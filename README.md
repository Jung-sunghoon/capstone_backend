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
여기서 필요없는 정보는 NULL로 보내도 됨

### project-edit-controller
```
프로젝트 구인글을 수정하는 기능
projectTitle, description, ProjectStatus, status, recruitmentCount를 받아와 해당 projectId에 해당하는 5가지 값을 수정
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

### project-more-information-controller
```
프로젝트 구인글에 자세한 정보를 가져오는 기능
projectId에 대한 값을 입력하면 모든 값을 가져옴
```

### sign-up-controller
```
회원 가입 기능
userId, password, name, nickname, email, gitAddress를 입력받으면 db에 추가
```

### search-project-controller
```
프로젝트 구인글 검색 기능
사용자가 어떤 text를 입력하면 projectTitle을 검색해 그 중 해당하는 검색어가 있는 프로젝트 구인글들의 정보를 반환
```

### login-controller
```
로그인 기능
userId 와 passoword를 입력해 둘이 일치하면 로그인 성공을 반환, 일치하지 않으면 로그인 실패
```

### project-generate-controller
```
프로젝트 구인글 생성 기능
projectTitle, description, userId, recruitmentCount를 입력하면 나머지 값들은 자동으로 생성
-> projectIId 자동 증가 생성, ProjectStatus = 0 (프로젝트 진행중), status = 0(구인 중), likes =0, views = 0 , generateData = 현재 시간(api 내부에서 처리)
```

### comment-controller /comments
```
댓글 생성 기능
해당하는 projectId와 댓글 생성자 아이디 userId, 댓글 내용 content 를 가져와 DB에 추가, 나머지는 자동생성
-> commentId는 자동 증가 생성, createdAt 생성시간은 api 내부에서 처리
```

### comment-controller /comments/{projectId}
```
프로젝트 구인글의 댓글을 가져오는 기능
해당하는 projectId에 달려있는 모든 댓글 테이블의 모든 정보를 가져옴
```

### project-list-controller
```
모든 프로젝트 구인글 정보를 가져오는 기능
프로젝트 구인 테이블의 모든 리스트들의 정보를 가져옴
```

### point-ranking-controller
```
포인트 랭킹 기능
모든 유저를 포인트 순대로 나열후 userId, name, Point 3가지 정보를 가져옴
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
