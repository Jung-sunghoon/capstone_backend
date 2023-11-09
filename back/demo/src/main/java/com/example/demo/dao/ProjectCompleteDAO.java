package com.example.demo.dao;

public interface ProjectCompleteDAO {
    void giveCompletePoint(int projectId);

    void updateProjectStatusToComplete(int projectId);
    // 프로젝트 ID로 프로젝트 생성자의 userId 가져오기
    String getCreatorUserIdByProjectId(int projectId);
    // 특정 사용자에게 포인트 부여
    void givePointsToUser(String userId, int points);
    // 프로젝트 상태를 가져오는 메서드
    String getProjectStatus(int projectId);

}
