package com.example.demo.dao;

import com.example.demo.dto.ApplicationProjectDTO;

import java.util.List;

public interface ApplicationProjectDAO {
    void insertApplication(ApplicationProjectDTO applicationProjectDTO);
    ApplicationProjectDTO findApplicationByUserIdAndProjectId(String userId, int projectId);
    ApplicationProjectDTO findPassByUserIdAndProjectId(String userId, int projectId);

    int deleteApplication(String userId, int projectId);
    List<ApplicationProjectDTO> listByProjectId(int projectId);

    void rejectApplication(String userId, int projectId);
    List<ApplicationProjectDTO> getApplicationsByUserId(String userId);
    List<ApplicationProjectDTO> getPassByUserId(String userId);
    int cancelApplication(String userId, int projectId);

    String projectStatusCheck(int projectId);
}
