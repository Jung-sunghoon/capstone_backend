package com.example.demo.controller;

import com.example.demo.dao.ItInfoMoreInformationDAO;
import com.example.demo.dto.ItInformationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")

public class ItInfoMoreInformationController {
    @Autowired
    private ItInfoMoreInformationDAO itInfoMoreInformationDAO;

    @PostMapping("/single_information_itInformation")
    public ResponseEntity<?> ItInfoMoreInformation(@RequestParam int itInfoId) {
        //id에 해당하는 값 가져오기
        ItInformationDTO itInfo = itInfoMoreInformationDAO.ItInformation(itInfoId);
        if (itInfo == null) {
            return new ResponseEntity<>("해당 it정보가 없습니다", HttpStatus.NOT_FOUND);
        }
        //조회수 증가
        itInfoMoreInformationDAO.IncreaseViewCountItInfo(itInfoId);
        System.out.println(itInfo.getItInfoId()+"번 IT 정보 글 불러오기 성공");
        return new ResponseEntity<>(itInfo, HttpStatus.OK);
    }
}
