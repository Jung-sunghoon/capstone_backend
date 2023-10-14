package com.example.demo.dao;

import java.util.List;

import com.example.demo.dto.ProjectGenerateDTO;

public interface ProjectListDAO {
    List<ProjectGenerateDTO> getAllProjects();
    List<String> getTechStacksByProjectId(int projectId);
    ProjectGenerateDTO getProjectById(int projectId);
}



