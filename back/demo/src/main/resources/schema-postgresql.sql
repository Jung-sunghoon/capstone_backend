DROP DATABASE IF EXISTS capstone;
CREATE DATABASE capstone;

USE capstone;

CREATE TABLE users (
    userId VARCHAR(100) PRIMARY KEY,
    password VARCHAR(100),
    name VARCHAR(50),
    nickname VARCHAR(50),
    email VARCHAR(100),
    gitAddress VARCHAR(100),
    point int
);


CREATE TABLE projectGenerate (
    projectId int PRIMARY KEY,
    projectTitle VARCHAR(100),
    description VARCHAR(500),
    creatorId VARCHAR(50),
    recruitmentStatus VARCHAR(50),
    recruitmentCount int,
    generateDate varchar(50),
    acceptedID VARCHAR(200)
);

