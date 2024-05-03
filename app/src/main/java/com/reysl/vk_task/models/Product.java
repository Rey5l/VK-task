package com.reysl.vk_task.models;

public class Product {
    private String title;
    private String description;
    private String price;
    private String thumbnail;

    public Product (String title, String description, String price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }


    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice() {
        this.price = price;
    }

//    public String getThumbnail() {
//        return this.thumbnail;
//    }
//
//    public void setThumbnail(String thumbnail) {
//        this.thumbnail = thumbnail;
//    }

}
