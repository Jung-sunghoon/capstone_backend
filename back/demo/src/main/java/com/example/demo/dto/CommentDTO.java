package com.example.demo.dto;

public class CommentDTO {
    private int commentId;
    private int projectId;
    private String userId;
    private String content;
    private String createdAt;
    private String updatedAt;

    private int commentToUpdateId;

    private int commentToDeleteId;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getCommentToUpdateId() {
        return commentToUpdateId;
    }

    public void setCommentToUpdateId(int commentToUpdateId) {
        this.commentToUpdateId = commentToUpdateId;
    }
}
