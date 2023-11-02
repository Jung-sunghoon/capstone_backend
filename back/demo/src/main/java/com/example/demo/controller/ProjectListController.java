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

            ProjectGenerateRequest generateRequestRequest = new ProjectGenerateRequest();
            generateRequestRequest.setProjectId(project.getProjectId());
            generateRequestRequest.setProjectTitle(project.getProjectTitle());
            generateRequestRequest.setDescription(project.getDescription());
            generateRequestRequest.setUserId(project.getUserId());
            generateRequestRequest.setProjectStatus(project.getProjectStatus());
            generateRequestRequest.setStatus(project.getStatus());
            generateRequestRequest.setRecruitmentCount(project.getRecruitmentCount());
            generateRequestRequest.setGenerateDate(project.getGenerateDate());
            generateRequestRequest.setLikes(project.getLikes());
            generateRequestRequest.setViews(project.getViews());
            generateRequestRequest.setThumbnail(project.getThumbnail());

            generateRequestRequest.setTechIds(techId);

            if (project.getThumbnail() != null) {
                String imageUrl = "http://localhost:8090/api/project_image/" + project.getProjectId();
                generateRequestRequest.setThumbnail(imageUrl);
            }

            projectGenerateRequest.add(generateRequestRequest);
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

            ProjectGenerateRequest generateRequestRequest = new ProjectGenerateRequest();
            generateRequestRequest.setProjectId(project.getProjectId());
            generateRequestRequest.setProjectTitle(project.getProjectTitle());
            generateRequestRequest.setDescription(project.getDescription());
            generateRequestRequest.setUserId(project.getUserId());
            generateRequestRequest.setProjectStatus(project.getProjectStatus());
            generateRequestRequest.setStatus(project.getStatus());
            generateRequestRequest.setRecruitmentCount(project.getRecruitmentCount());
            generateRequestRequest.setGenerateDate(project.getGenerateDate());
            generateRequestRequest.setLikes(project.getLikes());
            generateRequestRequest.setViews(project.getViews());
            generateRequestRequest.setThumbnail(project.getThumbnail());

            generateRequestRequest.setTechIds(techId);

            if (project.getThumbnail() != null) {
                String imageUrl = "http://localhost:8090/api/project_image/" + project.getProjectId();
                generateRequestRequest.setThumbnail(imageUrl);
            }

            projectGenerateRequest.add(generateRequestRequest);
        }
        return ResponseEntity.ok(projectGenerateRequest);
    }

}

