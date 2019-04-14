package com.example.gogreen_android.controllers;

import com.example.gogreen_android.models.Statistics;

public class LoginController {
    public static Statistics statistics;
    public static String userName;



//    public void updateStatistics() {
//        StatisticsController.getModel(statistics);
//    }

    public static void setUserName(String username){
        userName = username;
    }
}
