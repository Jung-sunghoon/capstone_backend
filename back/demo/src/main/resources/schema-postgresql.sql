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
    point int DEFAULT 0
);


CREATE TABLE projectGenerate (
    projectId int PRIMARY KEY,
    projectTitle VARCHAR(100),
    description TEXT,
    creatorId VARCHAR(50),
    recruitmentStatus VARCHAR(50),
    recruitmentCount int,
    generateDate varchar(50),
    acceptedID VARCHAR(200)
);

CREATE TABLE Comments (
    commentId INT AUTO_INCREMENT PRIMARY KEY,
    projectId INT,
    userId VARCHAR(100),
    content TEXT,
    likeCount INT DEFAULT 0,
    dislikeCount INT DEFAULT 0,
    created_at varchar(50),
    updated_at varchar(50)
);
