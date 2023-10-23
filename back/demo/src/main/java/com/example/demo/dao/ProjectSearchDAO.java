package com.example.demo.dao;


import com.example.demo.dto.ProjectGenerateDTO;

import java.util.List;

public interface ProjectSearchDAO {

    List<ProjectGenerateDTO> SearchProject(String projectTitle);
    List<Integer> getTechStacksByProjectId(int projectId);

}
