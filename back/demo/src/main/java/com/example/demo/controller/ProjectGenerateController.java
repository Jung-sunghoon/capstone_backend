package com.example.demo.controller;

import com.example.demo.dao.ProjectEditDAO;
import com.example.demo.dao.ProjectGenerateDAO;
import com.example.demo.dto.ProjectGenerateDTO;
import com.example.demo.dto.ProjectGenerateRequest;
import com.example.demo.dto.ProjectTechMapping;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProjectGenerateController {

    @Autowired
    private ProjectGenerateDAO projectGenerateDAO;
    @Autowired
    private ProjectEditDAO projectEditDAO;

    @PostMapping("/generate_project")
    public ResponseEntity<String> generateProjectWithImage(@RequestBody ProjectGenerateRequest request) throws Exception {

        ProjectGenerateDTO project = new ProjectGenerateDTO();
        project.setProjectId(request.getProjectId());
        project.setProjectTitle(request.getProjectTitle());
        project.setDescription(request.getDescription());
        project.setUserId(request.getUserId());
        project.setProjectStatus(request.getProjectStatus());
        project.setStatus(request.getStatus());
        project.setRecruitmentCount(request.getRecruitmentCount());
        project.setGenerateDate(request.getGenerateDate());
        project.setLikes(request.getLikes());
        project.setViews(request.getViews());


        MultipartFile thumbnail = request.getThumbnail();
        List<Integer> techIds = request.getTechIds();

        /*
        ObjectMapper mapper = new ObjectMapper();
        ProjectGenerateDTO project = mapper.readValue(projectJson, ProjectGenerateDTO.class);
        System.out.println(projectJson);
        System.out.println(project);
        */

        ///////////프로젝트가 존재하는지 체크//////////////////
        if(project.getProjectId() >0 && projectGenerateDAO.checkProjectExists(project.getProjectId()) ==1 ){

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
                    .filter(name -> !techIds.contains(name))
                    .collect(Collectors.toList());
            List<Integer> toAdd = techIds.stream()
                    .filter(name -> !existingTechNames.contains(name))
                    .collect(Collectors.toList());

            toDelete.forEach(ids -> projectEditDAO.deleteTechName(project.getProjectId(), ids));
            toAdd.forEach(ids -> projectEditDAO.addTechName(project.getProjectId(), ids));


            return ResponseEntity.ok("프로젝트 수정 완료");
        }


        //////////////////////새로운 프로젝트 추가///////////////////
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
        
        //--------------기술 스택 등록---------------------
        List<Integer> duplicatedTechIds = new ArrayList<>();

        for(int techId : techIds) {
            // 기술 스택 존재 여부 확인
            Integer count = projectGenerateDAO.checkTechStackExists(project.getProjectId(), techId);

            if (count != null && count > 0) {
                duplicatedTechIds.add(techId);
                continue;
            }

            projectGenerateDAO.ProjectTechStack(project.getProjectId(), techId);
        }

        if (!duplicatedTechIds.isEmpty()) {
            return new ResponseEntity<>("다음 기술 스택들은 이미 등록되어 있습니다: " + duplicatedTechIds, HttpStatus.BAD_REQUEST);
        }


        return ResponseEntity.ok("글 올리기 성공");
    }



}