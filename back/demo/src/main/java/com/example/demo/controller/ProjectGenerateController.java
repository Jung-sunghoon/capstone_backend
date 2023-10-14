package com.example.demo.controller;

import com.example.demo.dao.ProjectGenerateDAO;
import com.example.demo.dto.ProjectGenerateDTO;
import com.example.demo.dto.ProjectTechMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProjectGenerateController {

    @Autowired
    private ProjectGenerateDAO projectGenerateDAO;

    @PostMapping("/generate_project")
    public ResponseEntity<String> generateProjectWithImage(
            @RequestPart("project") String projectJson,
            @RequestPart("thumbnail") MultipartFile thumbnail) throws Exception {

        ObjectMapper mapper = new ObjectMapper();
        ProjectGenerateDTO project = mapper.readValue(projectJson, ProjectGenerateDTO.class);

        Integer projectNum = projectGenerateDAO.ProjectNumCheck();
        if (projectNum != null && projectNum > 0) {
            project.setProjectId(projectNum + 1);
        } else {
            project.setProjectId(1);
        }

        long systemTime = System.currentTimeMillis();
        // 출력 형태를 위한 formmater
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        // format에 맞게 출력하기 위한 문자열 변환
        String dTime = formatter.format(systemTime);

        project.setGenerateDate(dTime);
        if (thumbnail != null && !thumbnail.isEmpty()) {
            try {
                byte[] bytes = thumbnail.getBytes();
                Path dirPath = Paths.get("src", "main", "uploaded_files","ProjectId_thumbnail",Integer.toString(project.getProjectId()));//
                if (Files.notExists(dirPath)) {
                    Files.createDirectories(dirPath);
                }
                Path filePath = dirPath.resolve(thumbnail.getOriginalFilename());
                Files.write(filePath, bytes);

                String imagePath = filePath.toString();
                //imageDAO.saveImage(imagePath);
                project.setThumbnail(imagePath);
                //return ResponseEntity.ok(imagePath);

            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().body("이미지 저장 중 오류 발생");
            }
        } else {
            //return ResponseEntity.badRequest().body("제공된 이미지가 없습니다.");
        }

        projectGenerateDAO.ProjectData(project);
        projectGenerateDAO.IncreasePointProjectGenerate((project.getUserId()));

        return ResponseEntity.ok("글 올리기 성공");
    }

    //기술 스택 등록
    @PostMapping("/generate_project_Tech")
    public ResponseEntity<String> projectTechGenerate(@RequestBody ProjectTechMapping request) {
        List<String> duplicatedTechIds = new ArrayList<>();

        for(String techName : request.getTechNames()) {
            // 기술 스택 존재 여부 확인
            Integer count = projectGenerateDAO.checkTechStackExists(request.getProjectId(), techName);

            if (count != null && count > 0) {
                duplicatedTechIds.add(techName);
                continue;
            }

            projectGenerateDAO.ProjectTechStack(request.getProjectId(), techName);
        }

        if (!duplicatedTechIds.isEmpty()) {
            return new ResponseEntity<>("다음 기술 스택들은 이미 등록되어 있습니다: " + duplicatedTechIds, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("기술 스택 등록 완료");
    }

}