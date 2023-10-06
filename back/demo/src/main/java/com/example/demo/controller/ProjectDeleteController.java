package com.example.demo.controller;

import com.example.demo.dao.ProjectDeleteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProjectDeleteController {
    @Autowired
    private ProjectDeleteDAO projectDeleteDAO;

    @DeleteMapping("/delete_project")
    public ResponseEntity<?> deleteProject(@RequestParam int projectId) {
        try {
            projectDeleteDAO.deleteProject(projectId);
            projectDeleteDAO.deleteProjectTechMapping(projectId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
