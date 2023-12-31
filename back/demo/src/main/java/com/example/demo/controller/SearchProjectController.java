package com.example.demo.controller;

import com.example.demo.dao.ProjectSearchDAO;
import com.example.demo.dto.ProjectEditRequest;
import com.example.demo.dto.ProjectGenerateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static java.sql.Types.NULL;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class SearchProjectController {

    @Autowired
    private ProjectSearchDAO projectSearchDAO;


    @PostMapping("/search_project")
    public ResponseEntity<?> projectSearch(@RequestParam String projectTitle) {

        List<ProjectGenerateDTO> results = projectSearchDAO.SearchProject(projectTitle);

        if (results.isEmpty()) {
            return new ResponseEntity<>("검색 결과가 없습니다", HttpStatus.NOT_FOUND);
        }

        List<ProjectEditRequest> projectEditRequest = new ArrayList<>();

        for(ProjectGenerateDTO project : results) {
            List<Integer> techId = projectSearchDAO.getTechStacksByProjectId(project.getProjectId());
            projectEditRequest.add(new ProjectEditRequest(project, techId));
        }

        return new ResponseEntity<>(projectEditRequest, HttpStatus.OK);
    }
}