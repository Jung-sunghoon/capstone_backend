package com.example.demo.controller;

import com.example.demo.dao.ApplicationDAO;
import com.example.demo.dto.ApplicationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ApplicationController {
    @Autowired
    private ApplicationDAO applicationDAO;

    //사용자가 프로젝트 구인글에 신청
    @PostMapping("/apply")
    public ResponseEntity<?> applyForProject(@RequestBody ApplicationDTO applicationDTO) {

        ApplicationDTO existingApplication = applicationDAO.findApplicationByUserIdAndProjectId(applicationDTO.getUserId(), applicationDTO.getProjectId());

        if(existingApplication != null) {
            return new ResponseEntity<>("이미 신청하셨습니다.", HttpStatus.BAD_REQUEST);
        }

        long systemTime = System.currentTimeMillis();
        // 출력 형태를 위한 formmater
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        // format에 맞게 출력하기 위한 문자열 변환
        String dTime = formatter.format(systemTime);
        applicationDTO.setApplyDate(dTime);

        applicationDAO.insertApplication(applicationDTO);
        return new ResponseEntity<>("신청 완료", HttpStatus.CREATED);
    }

    //사용자가 프로젝트 신청을 취소하는 api
    @DeleteMapping("/cancel_apply")
    public ResponseEntity<?> cancelApplication(@RequestBody ApplicationDTO applicationDTO) {

        int deletedRows = applicationDAO.deleteApplication(applicationDTO.getUserId(), applicationDTO.getProjectId());

        if (deletedRows > 0) {
            return new ResponseEntity<>("신청 취소 완료", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("신청 정보를 찾을 수 없습니다", HttpStatus.NOT_FOUND);
        }
    }

    //사용자가 자신이 신청한 프로젝트 정보를 조회하는 api
    @GetMapping("/my_applications")
    public ResponseEntity<?> getMyApplications(@RequestParam String userId) {

        List<ApplicationDTO> applications = applicationDAO.getApplicationsByUserId(userId);

        if (applications.isEmpty()) {
            return new ResponseEntity<>("신청한 프로젝트가 없습니다.", HttpStatus.BAD_REQUEST);

        } else {
            return new ResponseEntity<>(applications, HttpStatus.OK);
        }
    }


}
