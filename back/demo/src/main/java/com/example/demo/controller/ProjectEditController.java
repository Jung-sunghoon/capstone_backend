package com.example.demo.controller;

import com.example.demo.dao.ProjectEditDAO;
import com.example.demo.dto.ProjectEditRequest;
import com.example.demo.dto.ProjectGenerateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProjectEditController {
    @Autowired
    private ProjectEditDAO projectEditDAO;

    @PutMapping("/update_project")
    public ResponseEntity<?> editProject(
            @RequestPart(name="project", required=false) ProjectGenerateDTO project,
            @RequestPart(name="thumbnail", required=false) MultipartFile thumbnail,
            @RequestPart(name="techIds", required=false) List<Integer> newTechIds) throws Exception {
        try {
            /*
            ObjectMapper mapper = new ObjectMapper();
            ProjectGenerateDTO project = mapper.readValue(projectJson, ProjectGenerateDTO.class);
            */

            if(project.getProjectStatus().equals("Ps_co")){
                projectEditDAO.increasePointComplete(project.getUserId());
            }

            if (thumbnail != null && !thumbnail.isEmpty()) {
                // 기존 이미지 삭제 로직
                String existingImagePath = project.getThumbnail();
                if (existingImagePath != null && !existingImagePath.isEmpty()) {
                    try {
                        Path existingFilePath = Paths.get(existingImagePath);
                        if (Files.exists(existingFilePath)) {
                            Files.delete(existingFilePath);
                        }
                    } catch (IOException e) {
                        // 삭제에 실패한 경우 로그를 기록하거나 적절한 대응을 합니다.
                        e.printStackTrace();
                    }
                }

                // 새 이미지를 저장하는 로직
                byte[] bytes = thumbnail.getBytes();
                Path dirPath = Paths.get("src", "main", "uploaded_files", "ProjectId_thumbnail", Integer.toString(project.getProjectId()));
                if (Files.notExists(dirPath)) {
                    Files.createDirectories(dirPath);
                }
                Path filePath = dirPath.resolve(thumbnail.getOriginalFilename());
                Files.write(filePath, bytes);

                String imagePath = filePath.toString();
                project.setThumbnail(imagePath);
            }

            projectEditDAO.EditProject(project);

            // 기존 techIds 가져오기
            List<Integer> existingTechNames = projectEditDAO.getTechStacksByProjectId(project.getProjectId());
            // 비교 & 업데이트 로직

            List<Integer> toDelete = existingTechNames.stream()
                    .filter(name -> !newTechIds.contains(name))
                    .collect(Collectors.toList());
            List<Integer> toAdd = newTechIds.stream()
                    .filter(name -> !existingTechNames.contains(name))
                    .collect(Collectors.toList());

            toDelete.forEach(ids -> projectEditDAO.deleteTechName(project.getProjectId(), ids));
            toAdd.forEach(ids -> projectEditDAO.addTechName(project.getProjectId(), ids));

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
