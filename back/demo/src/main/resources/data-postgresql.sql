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

INSERT INTO techStack(techName) /* 아래는 예시 데이터 셋임 1번부터 시작*/
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

INSERT INTO projectTechMapping(projectId, techId)
VALUES
(1,1),
(1,5),
(1,6);