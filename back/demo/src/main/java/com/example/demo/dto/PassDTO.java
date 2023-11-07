package com.example.demo.dto;

public class PassDTO {
    private String userId;
    private int projectId;
    private String status;
    private String passDate;

    public void setPassDate(String passDate) {
        this.passDate = passDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassDate() {
        return passDate;
    }


    public String getStatus() {
        return status;
    }

    public int getProjectId() {
        return projectId;
    }

}
