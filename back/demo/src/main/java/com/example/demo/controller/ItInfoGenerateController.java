package com.example.demo.controller;

import com.example.demo.dao.ItInfoGenerateDAO;
import com.example.demo.dto.ItInfoGenerateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Locale;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api")
public class ItInfoGenerateController {

    @Autowired
    private ItInfoGenerateDAO itInfoGenerateDAO;

    @PostMapping("/generate_it_info")
    public ResponseEntity<String> generateItInfo(@RequestBody ItInfoGenerateDTO request) {

        ItInfoGenerateDTO itInfo = new ItInfoGenerateDTO();


        if (request.getTitle() != null) itInfo.setTitle(request.getTitle());
        if (request.getDescription() != null) itInfo.setDescription(request.getDescription());
        if (request.getGenerateDate() != null) itInfo.setGenerateDate(request.getGenerateDate());
        if (request.getViews() != null) itInfo.setViews(request.getViews());


        if (itInfo.getGenerateDate() == null) {
            long systemTime = System.currentTimeMillis();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
            String dTime = formatter.format(systemTime);
            itInfo.setGenerateDate(dTime);
        }

        itInfoGenerateDAO.saveItInfo(itInfo);

        return ResponseEntity.ok("IT 정보 등록 완료");
    }
}
