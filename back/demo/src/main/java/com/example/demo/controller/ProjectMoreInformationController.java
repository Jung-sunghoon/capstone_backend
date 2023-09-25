package com.example.demo.controller;

import com.example.demo.dao.ProjectMoreInformationDAO;
import com.example.demo.dto.ProjectGenerateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProjectMoreInformationController {

    @Autowired
    private ProjectMoreInformationDAO projectMoreInformationDAO;


    @PostMapping("/single_information_project")
    public ResponseEntity<?> projectInformation(@RequestParam int projectId) {

        ProjectGenerateDTO results = projectMoreInformationDAO.ProjectInformation(projectId);
        projectMoreInformationDAO.IncreaseViewCount(projectId);
        
        if (results == null) {
            return new ResponseEntity<>("해당 프로젝트가 없습니다", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(results, HttpStatus.OK);
    }
}