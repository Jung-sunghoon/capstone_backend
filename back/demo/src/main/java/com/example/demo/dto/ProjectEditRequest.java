package com.example.demo.dto;

import java.util.List;

public class ProjectEditRequest {
    private ProjectGenerateDTO projectInfo;
    private List<Integer> techId;
    private String thumbnail;

    public ProjectEditRequest(ProjectGenerateDTO project, List<Integer> techId) {
        this.projectInfo = project;
        this.techId = techId;
    }

    public List<Integer> getTechId() {
        return techId;
    }

    public void setTechId(List<Integer> techId) {
        this.techId = techId;
    }

    public void setProjectInfo(ProjectGenerateDTO projectInfo) {
        this.projectInfo = projectInfo;
    }

    public ProjectGenerateDTO getProjectInfo() {
        return projectInfo;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
