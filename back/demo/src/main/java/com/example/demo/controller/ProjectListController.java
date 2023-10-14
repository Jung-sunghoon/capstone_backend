package com.example.demo.controller;

import com.example.demo.dao.ProjectListDAO;
import com.example.demo.dto.ProjectEditRequest;
import com.example.demo.dto.ProjectGenerateDTO;
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
    public ResponseEntity<List<ProjectEditRequest>> getAllProjects() {
        List<ProjectGenerateDTO> projects = projectListDAO.getAllProjects();

        // ProjectEditRequest 리스트 생성
        List<ProjectEditRequest> projectEditRequest = new ArrayList<>();

        for(ProjectGenerateDTO project : projects) {
            List<String> techNames = projectListDAO.getTechStacksByProjectId(project.getProjectId());
            ProjectEditRequest editRequest = new ProjectEditRequest(project, techNames);

            if (project.getThumbnail() != null) {
                String imageUrl = "http://localhost:8090/api/project_image/" + project.getProjectId();
                editRequest.setThumbnail(imageUrl);
            }

            projectEditRequest.add(editRequest);
        }
        return ResponseEntity.ok(projectEditRequest);
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

}

