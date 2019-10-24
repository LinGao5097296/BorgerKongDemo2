package com.demo.borgerkongdemo.database;

import android.content.Context;

import com.demo.borgerkongdemo.R;

import java.util.ArrayList;

public class FoodProvider {

    public static ArrayList<Food> getEmails(Context context){

        ArrayList<String[]> rawEmails = new ArrayList<>();
        rawEmails.add(context.getResources().getStringArray(R.array.food_1));
        rawEmails.add(context.getResources().getStringArray(R.array.foood_2));
        rawEmails.add(context.getResources().getStringArray(R.array.food_3));
        rawEmails.add(context.getResources().getStringArray(R.array.food_4));
        rawEmails.add(context.getResources().getStringArray(R.array.food_5));
        rawEmails.add(context.getResources().getStringArray(R.array.food_6));
        rawEmails.add(context.getResources().getStringArray(R.array.food_7));
        rawEmails.add(context.getResources().getStringArray(R.array.food_8));
        rawEmails.add(context.getResources().getStringArray(R.array.food_9));
        rawEmails.add(context.getResources().getStringArray(R.array.food_10));
        rawEmails.add(context.getResources().getStringArray(R.array.food_11));
        rawEmails.add(context.getResources().getStringArray(R.array.food_12));
        rawEmails.add(context.getResources().getStringArray(R.array.food_13));
        rawEmails.add(context.getResources().getStringArray(R.array.food_14));
        rawEmails.add(context.getResources().getStringArray(R.array.food_15));

        ArrayList<Food> foods = new ArrayList<>();
        for(int i=0;i<rawEmails.size();i++) {// String[] rawEmail: rawEmails)
            foods.add(new Food(rawEmails.get(i)[1], rawEmails.get(i)[2], rawEmails.get(i)[3],i+1+""));
        }
        return foods;
    }

}
