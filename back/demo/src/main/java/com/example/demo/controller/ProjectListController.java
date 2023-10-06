package com.example.demo.controller;

import com.example.demo.dao.ProjectListDAO;
import com.example.demo.dto.ProjectEditRequest;
import com.example.demo.dto.ProjectGenerateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProjectListController {

    @Autowired
    private ProjectListDAO projectListDAO;

    @GetMapping("/projects_list")
    public ResponseEntity<List<ProjectEditRequest>> getAllProjects() {
        List<ProjectGenerateDTO> projects = projectListDAO.getAllProjects();

        // ProjectEditRequest 리스트 생성
        List<ProjectEditRequest> projectEditRequest = new ArrayList<>();

        for(ProjectGenerateDTO project : projects) {
            List<Integer> techIds = projectListDAO.getTechStacksByProjectId(project.getProjectId());
            projectEditRequest.add(new ProjectEditRequest(project, techIds));
        }

        return ResponseEntity.ok(projectEditRequest);
    }
}

