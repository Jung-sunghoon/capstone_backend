package com.example.demo.dto;

import java.util.List;

public class ProjectTechMapping {
    private int projectId;
    private List<Integer> techIds;

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getProjectId() {
        return projectId;
    }

    public List<Integer> getTechIds() {
        return techIds;
    }
    public void setTechIds(List<Integer> techIds) {
        this.techIds = techIds;
    }


}
