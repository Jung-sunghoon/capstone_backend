package com.example.demo.controller;

import com.example.demo.dao.ProjectEditDAO;
import com.example.demo.dto.ProjectEditRequest;
import com.example.demo.dto.ProjectGenerateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProjectEditController {
    @Autowired
    private ProjectEditDAO projectEditDAO;

    @PutMapping("/update_project")
    public ResponseEntity<?> editProject(@RequestBody ProjectEditRequest request) {
        try {
            ProjectGenerateDTO project = request.getProjectInfo();
            if(project.getProjectStatus().equals("Ps_co")){
                projectEditDAO.increasePointComplete(project.getUserId());
            }
            projectEditDAO.EditProject(project);

            // 기존 techIds 가져오기
            List<String> existingTechNames = projectEditDAO.getTechStacksByProjectId(project.getProjectId());

            // 비교 & 업데이트
            List<String> newTechNames = request.getTechNames();

            // 삭제할 techNames 찾기: existing에는 있지만 newTechNames에는 없는 아이디
            List<String> toDelete = existingTechNames.stream()
                    .filter(name -> !newTechNames.contains(name))
                    .collect(Collectors.toList());
            // 추가할 techNames 찾기: techNames에는 있지만 existing에는 없는 아이디
            List<String> toAdd = newTechNames.stream()
                    .filter(name -> !existingTechNames.contains(name))
                    .collect(Collectors.toList());

            // DB 업데이트
            toDelete.forEach(name -> projectEditDAO.deleteTechName(project.getProjectId(), name));
            toAdd.forEach(name -> projectEditDAO.addTechName(project.getProjectId(), name));

            return new ResponseEntity<>(HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
