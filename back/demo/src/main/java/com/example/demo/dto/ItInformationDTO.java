package com.example.demo.dto;

public class ItInformationDTO {
    private int itInfoId;
    private String title;
    private String description;
    private String generateDate;
    private int views;

    public void setGenerateDate(String generateDate) {
        this.generateDate = generateDate;
    }

    public String getGenerateDate() {
        return generateDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getItInfoId() {
        return itInfoId;
    }

    public String getTitle() {
        return title;
    }

    public void setItInfoId(int itInfoId) {
        this.itInfoId = itInfoId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}
