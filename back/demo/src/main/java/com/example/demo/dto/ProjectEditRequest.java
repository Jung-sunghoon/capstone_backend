package com.example.demo.dto;

import java.util.List;

public class ProjectEditRequest {
    private ProjectGenerateDTO projectInfo;
    private List<Integer> techIds;

    public ProjectEditRequest(ProjectGenerateDTO project, List<Integer> techIds) {
        this.projectInfo = project;
        this.techIds = techIds;
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
}
