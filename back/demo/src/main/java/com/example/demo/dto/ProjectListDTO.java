package com.example.demo.dto;

public class ProjectListDTO {
    private int projectId;
    private String projectTitle;
    private String description;
    private String creatorId;
    private String recruitmentStatus;
    private int recruitmentCount;
    private String generateDate;
    private String acceptedID;
    private int likes;
    private int views;

    // getters and setters

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getRecruitmentStatus() {
        return recruitmentStatus;
    }

    public void setRecruitmentStatus(String recruitmentStatus) {
        this.recruitmentStatus = recruitmentStatus;
    }

    public int getRecruitmentCount() {
        return recruitmentCount;
    }

    public void setRecruitmentCount(int recruitmentCount) {
        this.recruitmentCount = recruitmentCount;
    }

    public String getGenerateDate() {
        return generateDate;
    }

    public void setGenerateDate(String generateDate) {
        this.generateDate = generateDate;
    }

    public String getAcceptedID() {
        return acceptedID;
    }

    public void setAcceptedID(String acceptedID) {
        this.acceptedID = acceptedID;
    }
    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getViews() {
        return views;
    }

    @Override
    public String toString() {
        return "ProjectListDTO{" +
                "projectId=" + projectId +
                ", projectTitle='" + projectTitle + '\'' +
                ", description='" + description + '\'' +
                ", creatorId='" + creatorId + '\'' +
                ", recruitmentStatus='" + recruitmentStatus + '\'' +
                ", recruitmentCount=" + recruitmentCount +
                ", generateDate='" + generateDate + '\'' +
                ", acceptedID='" + acceptedID + '\'' +
                '}';
    }
}

