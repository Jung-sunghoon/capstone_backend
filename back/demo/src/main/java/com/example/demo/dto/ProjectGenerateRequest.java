package com.example.demo.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ProjectGenerateRequest {
    private Integer projectId;
    private String projectTitle;
    private String description;
    private String userId;
    private String projectStatus;
    private String status;
    private Integer recruitmentCount;
    private String generateDate;
    private Integer likes;
    private Integer views;
    private String thumbnail;
    private List<Integer> techIds;

    // 기본 생성자
    public ProjectGenerateRequest() {}

    // Getter와 Setter 메서드
    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getRecruitmentCount() {
        return recruitmentCount;
    }

    public void setRecruitmentCount(Integer recruitmentCount) {
        this.recruitmentCount = recruitmentCount;
    }

    public String getGenerateDate() {
        return generateDate;
    }

    public void setGenerateDate(String generateDate) {
        this.generateDate = generateDate;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }


    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<Integer> getTechIds() {
        return techIds;
    }

    public void setTechIds(List<Integer> techIds) {
        this.techIds = techIds;
    }
}
