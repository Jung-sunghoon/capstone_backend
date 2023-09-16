package com.example.demo.controller;

import com.example.demo.dao.ProjectListDAO;
import com.example.demo.dto.ProjectListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProjectListController {

    @Autowired
    private ProjectListDAO projectListDAO;

    @GetMapping("/projects")
    public ResponseEntity<List<ProjectListDTO>> getAllProjects() {
        List<ProjectListDTO> projects = projectListDAO.getAllProjects();

        return ResponseEntity.ok(projects);
    }
}

