package com.example.demo.dao;

import com.example.demo.dto.ProjectGenerateDTO;


import java.util.List;

public interface ProjectGenerateDAO {
    void ProjectData(ProjectGenerateDTO project);

    Integer ProjectNumCheck();

    void IncreasePointProjectGenerate(String userId);
    void ProjectTechStack(int projectId, String techName);
    public Integer checkTechStackExists(int projectId, String techName);

}
