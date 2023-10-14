package com.example.demo.dto;

import java.util.List;

public class ProjectEditRequest {
    private ProjectGenerateDTO projectInfo;
    private List<String> techNames;
    private String thumbnail;

    public ProjectEditRequest(ProjectGenerateDTO project, List<String> techNames) {
        this.projectInfo = project;
        this.techNames = techNames;
    }

    public List<String> getTechNames() {
        return techNames;
    }

    public void setTechNames(List<String> techNames) {
        this.techNames = techNames;
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
