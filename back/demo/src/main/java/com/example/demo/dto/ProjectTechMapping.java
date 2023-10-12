package com.example.demo.dto;

import java.util.List;

public class ProjectTechMapping {
    private int projectId;
    private List<String> techNames;

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setTechNames(List<String> techNames) {
        this.techNames = techNames;
    }

    public List<String> getTechNames() {
        return techNames;
    }
}
