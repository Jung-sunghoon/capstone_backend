package com.example.demo.controller;

import com.example.demo.dao.ProjectGenerateDAO;
import com.example.demo.dto.ProjectGenerateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Locale;

import static java.sql.Types.NULL;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProjectGenerateController {

    @Autowired
    private ProjectGenerateDAO projectGenerateDAO;


    @PostMapping("/generate_project")
    public ResponseEntity<String> projectGenerate(@RequestBody ProjectGenerateDTO project) {

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

        projectGenerateDAO.ProjectData(project);

        return ResponseEntity.ok("글 올리기 성공");
    }
}