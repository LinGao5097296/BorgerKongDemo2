package com.demo.borgerkongdemo.database;

import java.io.Serializable;

public class Order implements Serializable {
    private int id;
    private String bid;
    private String title;
    private String content;
    private String time;
    private String img;
    public Order() {
        super();
    }

    public Order(String bid, String title, String content, String price, String img) {
        super();
        this.bid=bid;
        this.title=title;
        this.content = content;
        this.time=price;
        this.img=img;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
