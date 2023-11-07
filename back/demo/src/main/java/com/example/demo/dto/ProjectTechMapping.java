package com.example.demo.dto;

import java.util.List;

public class ProjectTechMapping {
    private int projectId;
    private List<Integer> techId;

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setTechId(List<Integer> techId) {
        this.techId = techId;
    }

    public List<Integer> getTechId() {
        return techId;
    }
}
