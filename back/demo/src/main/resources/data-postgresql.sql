
INSERT INTO users(userId, password, name, department, studentNumber, nickname, email, gitAddress, point, techStacks)
VALUES
('alice123', 'password123', 'Alice Kim', '컴퓨터공학과', 202055000, 'alice', 'alice.kim@email.com', 'https://github.com/alicekim', 100,'sql java'),
('bob456', 'password456', 'Bob Lee', '인공지능', 201500001,'bob', 'bob.lee@email.com', 'https://github.com/boblee', 100, 'python'),
('charlie789', 'password789', 'Charlie Park', '지능형IoT', 201899111,'charlie', 'charlie.park@email.com', 'https://github.com/charliepark', 0, 'sql python'),
('david101', 'password101', 'David Cho', '데이터사이언스', 201898222,'david', 'david.cho@email.com', 'https://github.com/davidcho', 0, 'php, ubuntu');

INSERT INTO users(userId, password, name, department) /* 관리자 계정 추가 */
VALUES ('master','master','mater','관리자');

INSERT INTO projectGenerate(projectId, projectTitle, description, userId, projectStatus, status, recruitmentCount, generateDate, likes, views,thumbnail)
VALUES
(1,'테스트 프로젝트','html 및 text', 'alice123', 'Ps_pr', 'S_pr', 2, '2023-09-14 10:34', 0, 0,'src\\main\\uploaded_files\\ProjectId_thumbnail\\1\\tset.png'),
(2,'테스트 프로젝트2','밥이만든 테스트 프로젝트 ', 'bob456', 'Ps_pr', 'S_pr', 5, '2023-09-14 10:34', 0, 0,NULL);


INSERT INTO projectTechMapping(projectId, techId)
VALUES
(1,1),
(1,5),
(1,6),
(2,6);

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