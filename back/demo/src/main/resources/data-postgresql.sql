
INSERT INTO users(userId, password, name, nickname, email, gitAddress, point)
VALUES
('alice123', 'password123', 'Alice Kim', 'alice', 'alice.kim@email.com', 'https://github.com/alicekim', 100),
('bob456', 'password456', 'Bob Lee', 'bob', 'bob.lee@email.com', 'https://github.com/boblee', 100),
('charlie789', 'password789', 'Charlie Park', 'charlie', 'charlie.park@email.com', 'https://github.com/charliepark', 0),
('david101', 'password101', 'David Cho', 'david', 'david.cho@email.com', 'https://github.com/davidcho', 0);

INSERT INTO projectGenerate(projectId, projectTitle, description, userId, projectStatus, status, recruitmentCount, generateDate, likes, views,thumbnail)
VALUES
(1,'테스트 프로젝트','html 및 text', 'alice123', 'Ps_pr', 'S_pr', 2, '2023-09-14 10:34', 0, 0,'src\\main\\uploaded_files\\ProjectId_thumbnail\\1\\tset.png'),
(2,'테스트 프로젝트2','밥이만든 테스트 프로젝트 ', 'bob456', 'Ps_pr', 'S_pr', 5, '2023-09-14 10:34', 0, 0,NULL);


INSERT INTO projectTechMapping(projectId, techName)
VALUES
(1,'Java'),
(1,'CSS'),
(1,'SQL'),
(2,'SQL');

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