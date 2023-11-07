package com.example.demo.dao;

import com.example.demo.dto.ProjectGenerateDTO;

import java.util.List;

public interface ProjectEditDAO {
    void EditProject(ProjectGenerateDTO project);
    void increasePointComplete(String userId);

    List<Integer> getTechStacksByProjectId(int projectId);
    void addTechName(int projectId, int techId);
    void deleteTechName(int projectId, int techId);
}
