package com.example.demo.controller;

import com.example.demo.dao.ItInfoListDAO;
import com.example.demo.dto.ItInfoListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ItInfoListController {

    @Autowired
    private ItInfoListDAO itInfoListDAO;

    @GetMapping("/it-info-list")
    public ResponseEntity<List<ItInfoListDTO>> getItInfoList() {
        List<ItInfoListDTO> itInfoList = itInfoListDAO.getItInfoList();

        if (itInfoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(itInfoList);
    }
}

