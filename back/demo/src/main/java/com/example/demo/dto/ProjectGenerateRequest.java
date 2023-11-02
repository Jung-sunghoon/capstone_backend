package com.example.demo.dto;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;

public class ProjectGenerateRequest {
    private ProjectGenerateDTO project;
    private MultipartFile thumbnail;
    private List<Integer> techIds;

    // 기본 생성자
    public ProjectGenerateRequest() {}

    // 모든 필드를 포함하는 생성자
    public ProjectGenerateRequest(ProjectGenerateDTO project, MultipartFile thumbnail, List<Integer> techIds) {
        this.project = project;
        this.thumbnail = thumbnail;
        this.techIds = techIds;
    }

    // Getter와 Setter 메서드
    public ProjectGenerateDTO getProject() {
        return project;
    }

    public void setProject(ProjectGenerateDTO project) {
        this.project = project;
    }

    public MultipartFile getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MultipartFile thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<Integer> getTechIds() {
        return techIds;
    }

    public void setTechIds(List<Integer> techIds) {
        this.techIds = techIds;
    }
}
