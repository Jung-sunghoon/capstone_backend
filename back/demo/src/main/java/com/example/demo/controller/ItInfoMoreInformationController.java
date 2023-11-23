package com.example.demo.controller;

import com.example.demo.dao.ItInfoMoreInformationDAO;
import com.example.demo.dto.ItInformation;
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

        ItInformation itInfo = itInfoMoreInformationDAO.ItInformation(itInfoId);
        if (itInfo == null) {
            return new ResponseEntity<>("해당 it정보가 없습니다", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(itInfo, HttpStatus.OK);
    }
}
