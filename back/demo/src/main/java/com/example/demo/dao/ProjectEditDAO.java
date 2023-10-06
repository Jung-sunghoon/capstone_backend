package com.example.demo.dao;

import com.example.demo.dto.ProjectGenerateDTO;

import java.util.List;

public interface ProjectEditDAO {
    void EditProject(ProjectGenerateDTO project);
    void increasePointComplete(String userId);

    List<Integer> getTechStacksByProjectId(int projectId);
    void addTechId(int projectId, int techId);
    void deleteTechId(int projectId, int techId);
}
