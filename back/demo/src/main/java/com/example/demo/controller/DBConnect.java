package com.example.demo.controller;

import com.example.demo.dto.TestDTO;
import com.example.demo.dao.TestDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DBConnect{

    @Autowired
    private TestDAO testDAO;

    @GetMapping("/hello2")
    public List<TestDTO> HelloWorld() {
        return testDAO.getTestData();
    }
}