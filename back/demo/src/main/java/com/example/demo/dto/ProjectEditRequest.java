package com.example.demo.dto;

import java.util.List;

public class ProjectEditRequest {
    private ProjectGenerateDTO projectInfo;
    private List<Integer> techIds;
    private String thumbnail;

    public ProjectEditRequest(ProjectGenerateDTO project, List<Integer> techId) {
        this.projectInfo = project;
        this.techIds = techId;
    }

    public List<Integer> getTechIds() {
        return techIds;
    }

    public void setTechIds(List<Integer> techIds) {
        this.techIds = techIds;
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
