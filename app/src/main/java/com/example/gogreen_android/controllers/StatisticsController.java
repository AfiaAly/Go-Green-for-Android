package com.example.gogreen_android.controllers;

import android.os.AsyncTask;

import com.example.gogreen_android.models.Profile;
import com.example.gogreen_android.models.Statistics;
import com.example.gogreen_android.models.VegetarianMeal;
import com.example.gogreen_android.requests.GetRequests;

import java.io.IOException;
import java.util.ArrayList;

public class StatisticsController {


    public static String userName;
    public static GetRequests getRequests;
    public static VegetarianMeal vegMeal;
    public static Statistics statistics;
    public static Profile profile;

    public void initialise(){
        updateStatistics();
    }

    class AsyncTaskConnection extends AsyncTask<ArrayList, Void, Void> {

        String username;

        @Override
        protected Void doInBackground(ArrayList... arrayLists) {
            username = (String) arrayLists[0].get(0);
            try {
                Statistics statistics = getRequests.getStatsConnection(username);
                System.out.println("New statistics object at StatisticsController.java by AsyncTask is: " + statistics);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("IO Error occured at AsyncTask at StatsController.java");
            }
            System.out.println("Got stats through getModel() in StatisticsController.java with username: " + username);
            return null;
        }
    }

    public static void setUserName(String user){
        userName = user;
    }

    public void updateStatistics(){
        final int normalCar = statistics.getUsualTravelDistanceByCar();
        final int byBike = statistics.getTotalTravelDistanceByBike();
        final int byPt = statistics.getTotalTravelDistanceByPublicTransport();
        final int numVegetarianMeals = vegMeal.getNumberOfMeals();
        final int numLocalProducts = statistics.getNumberOfLocalProduce();

        final int totalEmission = statistics.getTotalReducedEmission();
        final int vegetarianEmission = statistics.getReducedEmissionByVegetarianMeal();
        final int bikeEmission = statistics.getReducedEmissionByTravelingByBike();
        final int publicTransportEmission = statistics.getReducedEmissionByTravelingByPublicTransport();
        final int productsEmission = statistics.getReducedEmissionByBuyingLocalProducts();
        final int panelEmission = statistics.getReducedEmissionBySolarPanels();
        final int temperatureEmission = statistics.getReducedEmissionByRoomTemperature();

        final int vegStreak = statistics.getVeggieMealsStreakNumber();
        final int bikeStreak = statistics.getBikeStreakNumber();
    }
}
