package com.example.demo.controller;

import com.example.demo.dao.TechStackDAO;
import com.example.demo.dto.TechStackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")

public class TechStackController {

    @Autowired
    private TechStackDAO techStackDAO;

    @GetMapping("/techstacks")
    public ResponseEntity<List<TechStackDTO>> getAllTechStacks() {
        List<TechStackDTO> techStacks = techStackDAO.getAllTechStacks();

        if (techStacks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(techStacks);
    }
}
