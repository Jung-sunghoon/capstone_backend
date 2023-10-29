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
public class ProjectGenerateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGenerateProjectWithImage() throws Exception {
        // 테스트에 사용될 프로젝트 객체를 준비합니다.
        ProjectGenerateDTO project = new ProjectGenerateDTO();
        //project.setProjectId(0);
        project.setProjectTitle("테스트 프로젝트2222222222");
        project.setDescription("스프링부트 자체에서 만들어진 새로운 프로젝트 입니다.");
        project.setUserId("alice123");
        project.setProjectStatus("Ps_pr");
        project.setStatus("S_pr");
        project.setRecruitmentCount(3);

        ObjectMapper objectMapper = new ObjectMapper();
        String projectJson = objectMapper.writeValueAsString(project);
        MockMultipartFile projectPart = new MockMultipartFile("project", "", "application/json", projectJson.getBytes());

        MockMultipartFile imageFile = new MockMultipartFile(
                "thumbnail",
                "filename.jpg",
                "image/jpeg",
                "image bytes".getBytes()
        );

        List<Integer> techIds = Arrays.asList(1, 2, 5);
        String techIdsJson = objectMapper.writeValueAsString(techIds);
        MockMultipartFile techIdsPart = new MockMultipartFile("techIds", "", "application/json", techIdsJson.getBytes());

        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/generate_project")
                        .file(imageFile)
                        .file(projectPart)
                        .file(techIdsPart)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(status().isOk());
    }
}
