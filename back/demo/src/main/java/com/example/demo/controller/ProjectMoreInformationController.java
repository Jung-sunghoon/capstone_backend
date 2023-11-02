package com.example.demo.controller;

import com.example.demo.dao.ProjectMoreInformationDAO;
import com.example.demo.dto.ProjectEditRequest;
import com.example.demo.dto.ProjectGenerateDTO;
import com.example.demo.dto.ProjectGenerateRequest;
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

        ProjectGenerateRequest generateRequest = new ProjectGenerateRequest();
        if(project.getProjectId() > -1) generateRequest.setProjectId(project.getProjectId());
        if(project.getProjectTitle() != null) generateRequest.setProjectTitle(project.getProjectTitle());
        if(project.getDescription() != null) generateRequest.setDescription(project.getDescription());
        if(project.getUserId() != null) generateRequest.setUserId(project.getUserId());
        if(project.getProjectStatus() != null) generateRequest.setProjectStatus(project.getProjectStatus());
        if(project.getStatus() != null) generateRequest.setStatus(project.getStatus());
        if(project.getRecruitmentCount() > -1) generateRequest.setRecruitmentCount(project.getRecruitmentCount());
        if(project.getGenerateDate() != null) generateRequest.setGenerateDate(project.getGenerateDate());
        if(project.getLikes() > -1) generateRequest.setLikes(project.getLikes());
        if(project.getViews() > -1) generateRequest.setViews(project.getViews());
        if(project.getThumbnail() != null) generateRequest.setThumbnail(project.getThumbnail());

        // 프로젝트와 관련된 techId 목록 가져오기
        List<Integer> techIds = projectMoreInformationDAO.getTechStacksByProjectId(projectId);
        generateRequest.setTechIds(techIds);

        projectMoreInformationDAO.IncreaseViewCount(projectId);
        projectMoreInformationDAO.IncreasePointProjectMoreInformation(project.getUserId());



        if (project.getThumbnail() != null) {
            String imageUrl = "http://localhost:8090/api/project_image/" + project.getProjectId();
            generateRequest.setThumbnail(imageUrl);
        }



        return new ResponseEntity<>(generateRequest, HttpStatus.OK);
    }
}