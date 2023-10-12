package com.example.demo.dao;

import com.example.demo.dto.ProjectGenerateDTO;

import java.util.List;

public interface ProjectEditDAO {
    void EditProject(ProjectGenerateDTO project);
    void increasePointComplete(String userId);

    List<String> getTechStacksByProjectId(int projectId);
    void addTechName(int projectId, String techName);
    void deleteTechName(int projectId, String techName);
}
