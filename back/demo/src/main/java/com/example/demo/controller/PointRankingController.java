package com.example.demo.controller;

import com.example.demo.dao.PointRankingDAO;
import com.example.demo.dto.PointRankDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class PointRankingController {
    @Autowired
    private PointRankingDAO pointRankingDAO;

    @GetMapping("/point_ranking")
    public ResponseEntity<?> getUserPointRanking() {
        try {
            List<PointRankDTO> users = pointRankingDAO.Ranking();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
