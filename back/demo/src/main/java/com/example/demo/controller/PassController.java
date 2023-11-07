package com.example.demo.controller;

import com.example.demo.dao.ApplicationProjectDAO;
import com.example.demo.dao.PassDAO;
import com.example.demo.dto.PassDTO;
import com.example.demo.dto.ApplicationProjectDTO;
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
public class PassController {
    @Autowired
    private ApplicationProjectDAO applicationProjectDAO;
    @Autowired
    private PassDAO passDAO;

    // 프로젝트에 신청한 신청자 목록 반환
    @GetMapping("/apply_list")
    public ResponseEntity<List<ApplicationProjectDTO>> getApplicantsForProject(@RequestParam int projectId) {
        List<ApplicationProjectDTO> applicantList = passDAO.getApplicationListByProject(projectId);
        return new ResponseEntity<>(applicantList, HttpStatus.OK);
    }

    //프로젝트 생성자가 합격자 선택하는 api
    @PostMapping("/accept")
    public ResponseEntity<?> acceptApplicant(@RequestBody PassDTO passDTO) {
        // 구인 총 인원수 체크
        int passCount = passDAO.countPassByProjectId(passDTO.getProjectId());
        int recruitmentCount = passDAO.countProjectGenerateByProjectId(passDTO.getProjectId());
        if (passCount == recruitmentCount) {
            passDAO.updateProjectGenerateStatus(passDTO.getProjectId());
            return new ResponseEntity<>("구인 인원수를 초과하였습니다.", HttpStatus.BAD_REQUEST);
        }


        long systemTime = System.currentTimeMillis();
        // 출력 형태를 위한 formmater
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
        // format에 맞게 출력하기 위한 문자열 변환
        String dTime = formatter.format(systemTime);
        passDTO.setPassDate(dTime);

        int deletedRows = applicationProjectDAO.deleteApplication(passDTO.getUserId(), passDTO.getProjectId()); //기존 신청 테이블에서 삭제

        if (deletedRows > 0) { // 삭제가 성공적으로 수행되었는지 확인
            passDAO.insertPass(passDTO); //합격자 추가
        } else {
            return new ResponseEntity<>("신청 정보를 확인해주세요", HttpStatus.INTERNAL_SERVER_ERROR);//db에 신청정보가 없다는 뜻
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //프로젝트 생성자가 신청자 거절하는 api
    @PostMapping("/reject")
    public ResponseEntity<?> rejectApplicant(@RequestBody ApplicationProjectDTO applicationProjectDTO) {

        applicationProjectDAO.rejectApplication(applicationProjectDTO.getUserId(), applicationProjectDTO.getProjectId());

        return new ResponseEntity<>("신청을 거절하였습니다.", HttpStatus.OK);

    }
}
