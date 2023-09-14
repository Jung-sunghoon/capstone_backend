package com.example.demo.dao;

import com.example.demo.dto.ProjectGenerateDTO;

public interface ProjectGenerateDAO {
    void ProjectData(ProjectGenerateDTO project);

    Integer ProjectNumCheck();
}
