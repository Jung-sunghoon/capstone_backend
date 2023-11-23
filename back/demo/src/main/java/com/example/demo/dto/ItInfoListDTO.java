package com.example.demo.dto;

public class ItInfoListDTO {
    private int itInfoId;
    private String title;
    private String generateDate;
    private int views;

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

    public String getGenerateDate() {
        return generateDate;
    }

    public void setGenerateDate(String generateDate) {
        this.generateDate = generateDate;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }
}

