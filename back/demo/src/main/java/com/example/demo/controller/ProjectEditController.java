package com.example.demo.controller;

import com.example.demo.dao.ProjectEditDAO;
import com.example.demo.dto.ProjectGenerateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProjectEditController {
    @Autowired
    private ProjectEditDAO projectEditDAO;

    @PutMapping("/update_project")
    public ResponseEntity<?> editProject(@RequestBody ProjectGenerateDTO project) {
        try {
            projectEditDAO.EditProject(project);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
