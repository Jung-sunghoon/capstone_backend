package com.example.demo.dao;

import com.example.demo.dto.ProjectGenerateDTO;

import java.util.List;

public interface ProjectMoreInformationDAO {
    ProjectGenerateDTO ProjectInformation(int projectId);

    void IncreaseViewCount(int projectId);

    void IncreasePointProjectMoreInformation(String userId);
    List<Integer> getTechStacksByProjectId(int projectId);
}
