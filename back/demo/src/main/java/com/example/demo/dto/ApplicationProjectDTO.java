package com.example.demo.dto;

public class ApplicationProjectDTO {
    private String userId;
    private int projectId;
    private String status;
    private String applyDate;


    public void setApplyDate(String applyDate) {
        this.applyDate = applyDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplyDate() {
        return applyDate;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
