package com.demo.borgerkongdemo.database;

import java.io.Serializable;

public class Food implements Serializable ,Comparable<Food>{

    private String price;
    private String title;
    private String desc;
    private String id;

    public Food(String price, String title, String desc, String id){
        this.price = price;
        this.title = title;
        this.desc = desc;
        this.id = id;
    }

    public String getPrice(){return price;}
    public String getTitle(){return title;}
    public String getDesc(){return desc;}
    public String getId(){return id;}

    @Override
    public int compareTo(Food o) {
        return 0;
    }
}
