package com.example.demo.controller;


import com.example.demo.dao.ProjectCompleteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ProjectCompleteController {
    @Autowired
    public ProjectCompleteDAO projectCompleteDAO;


    @PutMapping("/complete_project")
    public ResponseEntity<String> completeProject(@RequestParam int projectId) {
        try {
            // 현재 프로젝트 상태 확인
            String currentStatus = projectCompleteDAO.getProjectStatus(projectId);
            if ("Ps_co".equals(currentStatus)) {
                return new ResponseEntity<>("이미 완료된 프로젝트입니다.", HttpStatus.BAD_REQUEST);
            }
            // 프로젝트 상태를 완료로 변경
            projectCompleteDAO.updateProjectStatusToComplete(projectId);
            // 프로젝트 생성자의 userId 가져오기
            String creatorUserId = projectCompleteDAO.getCreatorUserIdByProjectId(projectId);

            // 프로젝트 생성자에게도 포인트 부여
            if (creatorUserId != null) {
                projectCompleteDAO.givePointsToUser(creatorUserId, 500);
            }

            // '팀원'만 포인트 부여
            projectCompleteDAO.giveCompletePoint(projectId);

            return new ResponseEntity<>("프로젝트 완료", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("프로젝트 완료 처리 중 에러가 발생하였습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
