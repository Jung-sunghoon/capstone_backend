package com.example.demo.controller;

import com.example.demo.dao.ProjectMoreInformationDAO;
import com.example.demo.dto.ProjectEditRequest;
import com.example.demo.dto.ProjectGenerateDTO;
import com.example.demo.dto.ProjectTechMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProjectMoreInformationController {

    @Autowired
    private ProjectMoreInformationDAO projectMoreInformationDAO;


    @PostMapping("/single_information_project")
    public ResponseEntity<?> projectInformation(@RequestParam int projectId) {

        ProjectGenerateDTO project = projectMoreInformationDAO.ProjectInformation(projectId);

        if (project == null) {
            return new ResponseEntity<>("해당 프로젝트가 없습니다", HttpStatus.NOT_FOUND);
        }
        // 프로젝트와 관련된 techId 목록 가져오기
        List<Integer> techIds = projectMoreInformationDAO.getTechStacksByProjectId(projectId);


        projectMoreInformationDAO.IncreaseViewCount(projectId);
        projectMoreInformationDAO.IncreasePointProjectMoreInformation(project.getUserId());

        ProjectEditRequest projectEditRequest = new ProjectEditRequest(project,techIds);
        if (project.getThumbnail() != null) {
            String imageUrl = "http://localhost:8090/api/project_image/" + project.getProjectId();
            projectEditRequest.setThumbnail(imageUrl);
        }



        return new ResponseEntity<>(projectEditRequest, HttpStatus.OK);
    }
}