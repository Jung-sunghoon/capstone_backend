package com.example.demo.dto;

public class ItInfoGenerateDTO {
    private int itInfoId;
    private String title;
    private String description;
    private String generateDate;
    private Integer views;

    public int getItInfoId() {
        return itInfoId;
    }

    public void setItInfoId(int itInfoId) {
        this.itInfoId = itInfoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenerateDate() {
        return generateDate;
    }

    public void setGenerateDate(String generateDate) {
        this.generateDate = generateDate;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

}

