# capstone_be

api 실행시 스웨거 주소

http://localhost:8080/swagger-ui/index.html

설정
========
intellig IDEA 사용
***
gradle 말고 maven 이용
***
DB는 MySQL 8.0.33 버전 이용 및 MySQL workbench를 이용

추가로 SQL을 유연하게 이용하기 위해 Mybatis(마이바티스) 사용
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

![Untitled](https://prod-files-secure.s3.us-west-2.amazonaws.com/80dbf90a-24ab-4280-85f3-5b38857f05a8/d151552e-3756-4664-a377-12f023a36330/Untitled.png)

위의 그림처럼 오른쪽 완전 끝부분 m Maven 클릭후 회전 재활용 모양(모든 maven 프로젝트 다시 로드) 버튼 누르면 적용됨(빨간색 글씨 사라짐)

***
back/demo/src/main/resources/application.properties

이 파일에 

spring.datasource.jdbc-url=jdbc:mysql://localhost:3307/capstone 

접속안될 시 3307 -> 3306 으로 변경




진행사항
==================
spring boot 실행 및 swagger 등록 성공

230912 로그인 및 회원가입 기능 구현

***
![image](https://github.com/Jung-sunghoon/capstone_be/assets/101784544/8bd0f45c-ae7a-4fc1-9f4a-22bdc79e2ce6)
***

??




현재 DB 생성문
====

```
DROP DATABASE IF EXISTS capstone;
CREATE DATABASE capstone;

USE capstone;

CREATE TABLE users (
    userId VARCHAR(100) PRIMARY KEY,
    password VARCHAR(100),
    name VARCHAR(50),
    nickname VARCHAR(50),
    email VARCHAR(100),
    gitAddress VARCHAR(100)
);
```
