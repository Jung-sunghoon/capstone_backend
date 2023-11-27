package com.example.demo.controller;

import com.example.demo.dao.ItInfoEditDAO;
import com.example.demo.dao.ItInfoGenerateDAO;
import com.example.demo.dto.ItInfoGenerateDTO;
import com.example.demo.dto.ItInfoEditRequest;
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
    @Autowired
    private ItInfoEditDAO itInfoEditDAO;

    @PostMapping("/generate_it_info")
    public ResponseEntity<String> generateOrUpdateItInfo(@RequestBody ItInfoEditRequest request) {

        if (request.getItInfoId() > 0 && itInfoEditDAO.checkItInfoExists(request.getItInfoId()) == 1) {

            ItInfoGenerateDTO itInfo = new ItInfoGenerateDTO();
            itInfo.setItInfoId(request.getItInfoId());
            if (request.getTitle() != null) itInfo.setTitle(request.getTitle());
            if (request.getDescription() != null) itInfo.setDescription(request.getDescription());

            itInfoEditDAO.updateItInfo(itInfo);

            return ResponseEntity.ok("IT 정보 글 수정 완료");

        } else {
            // Entry does not exist, create a new entry
            ItInfoGenerateDTO itInfo = new ItInfoGenerateDTO();
            if (request.getTitle() != null) itInfo.setTitle(request.getTitle());
            if (request.getDescription() != null) itInfo.setDescription(request.getDescription());

            if (itInfo.getGenerateDate() == null) {
                long systemTime = System.currentTimeMillis();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.KOREA);
                String dTime = formatter.format(systemTime);
                itInfo.setGenerateDate(dTime);
            }

            itInfoGenerateDAO.saveItInfo(itInfo);
            System.out.println("IT 정보 글 등록 성공");
            return ResponseEntity.ok("IT 정보 글 등록 완료");
        }
    }
}
