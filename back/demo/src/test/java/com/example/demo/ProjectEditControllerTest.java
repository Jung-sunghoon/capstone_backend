package com.example.demo;

import com.example.demo.dto.ProjectGenerateDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class ProjectEditControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testUpdateProject() throws Exception {
        // 1. 프로젝트 데이터 준비
        ProjectGenerateDTO project = new ProjectGenerateDTO();
        project.setProjectId(1);
        project.setProjectTitle("테스트 프로젝트 수정판");
        project.setDescription("스프링부트 자체에서 만들어진 새로운 프로젝트 입니다.");
        project.setUserId("alice123");
        project.setProjectStatus("Ps_co");
        project.setStatus("S_co");
        project.setRecruitmentCount(3);
        // 2. 이미지 파일 준비
        MockMultipartFile imageFile = new MockMultipartFile(
                "thumbnail",
                "testttttttttttttttt.jpg",
                "image/jpeg",
                "image bytes".getBytes()
        );

        // 3. 기술 ID 목록 준비
        List<Integer> techIds = Arrays.asList(1, 4);
        ObjectMapper objectMapper = new ObjectMapper();
        String techIdsJson = objectMapper.writeValueAsString(techIds);
        MockMultipartFile techIdsPart = new MockMultipartFile("techIds", "", "application/json", techIdsJson.getBytes());

        // 4. 프로젝트 객체를 JSON으로 변환
        String projectJson = objectMapper.writeValueAsString(project);
        MockMultipartFile projectPart = new MockMultipartFile("project", "", "application/json", projectJson.getBytes());

        // 5. PUT 요청 실행 및 응답 확인
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/update_project")
                        .file(imageFile)
                        .file(projectPart)
                        .file(techIdsPart)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .with(request -> { request.setMethod("PUT"); return request; }))
                .andExpect(status().isOk());
    }
}
