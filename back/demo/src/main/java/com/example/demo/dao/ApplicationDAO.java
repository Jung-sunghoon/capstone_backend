package com.example.demo.dao;

import com.example.demo.dto.ApplicationDTO;

import java.util.List;

public interface ApplicationDAO {
    void insertApplication(ApplicationDTO applicationDTO);
    ApplicationDTO findApplicationByUserIdAndProjectId(String userId, int projectId);

    int deleteApplication(String userId, int projectId);
    List<ApplicationDTO> listByProjectId(int projectId);

    void rejectApplication(String userId, int projectId);
    List<ApplicationDTO> getApplicationsByUserId(String userId);
}
