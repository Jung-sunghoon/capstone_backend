package com.example.demo.controller;

import com.example.demo.dao.ProjectListDAO;
import com.example.demo.dto.ProjectEditRequest;
import com.example.demo.dto.ProjectGenerateDTO;
import com.example.demo.dto.ProjectGenerateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProjectListController {

    @Autowired
    private ProjectListDAO projectListDAO;

    @GetMapping("/projects_list")
    public ResponseEntity<List<ProjectGenerateRequest>> getAllProjects() {
        List<ProjectGenerateDTO> projects = projectListDAO.getAllProjects();

        // projectGenerateRequest 리스트 생성
        List<ProjectGenerateRequest> projectGenerateRequest = new ArrayList<>();

        for (ProjectGenerateDTO project : projects) {
            List<Integer> techId = projectListDAO.getTechStacksByProjectId(project.getProjectId());

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


            generateRequest.setTechIds(techId);
            /*
            if (project.getThumbnail() != null) {
                String imageUrl = "http://localhost:8090/api/project_image/" + project.getProjectId();
                generateRequest.setThumbnail(imageUrl);
            }*/

            projectGenerateRequest.add(generateRequest);
        }
        return ResponseEntity.ok(projectGenerateRequest);
    }

    //이미지 주소를 통해 이미지를 가져오는 api
    @GetMapping("/project_image/{projectId}")
    public ResponseEntity<Resource> getProjectImage(@PathVariable int projectId) throws IOException {
        ProjectGenerateDTO project = projectListDAO.getProjectById(projectId);
        if (project != null && project.getThumbnail() != null) {
            Path path = Paths.get(project.getThumbnail());
            Resource resource = new ByteArrayResource(Files.readAllBytes(path));
            String contentType = Files.probeContentType(path);  // 파일의 컨텐츠 타입을 결정합니다.

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping("/projects/{userId}")
    public ResponseEntity<List<ProjectGenerateRequest>> getProjectsByUserId(@PathVariable String userId) {
        List<ProjectGenerateDTO> projects = projectListDAO.getProjectsByUserId(userId);

        // projectGenerateRequest 리스트 생성
        List<ProjectGenerateRequest> projectGenerateRequest = new ArrayList<>();

        for (ProjectGenerateDTO project : projects) {
            List<Integer> techId = projectListDAO.getTechStacksByProjectId(project.getProjectId());

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


            generateRequest.setTechIds(techId);

            /*
            if (project.getThumbnail() != null) {
                String imageUrl = "http://localhost:8090/api/project_image/" + project.getProjectId();
                generateRequest.setThumbnail(imageUrl);
            }*/

            projectGenerateRequest.add(generateRequest);
        }
        return ResponseEntity.ok(projectGenerateRequest);
    }

}

