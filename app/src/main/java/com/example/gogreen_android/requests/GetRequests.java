package com.example.gogreen_android.requests;

import android.os.AsyncTask;

import com.example.gogreen_android.models.Statistics;
import com.example.gogreen_android.models.VegetarianMeal;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;

public class GetRequests {

    public Statistics getStatistics(String username) {
        try {
            AsyncTaskConnection taskConnection = new AsyncTaskConnection();
            ArrayList array = new ArrayList(1);
            array.add(0, username);
            taskConnection.execute(array);
            Statistics result = (Statistics) taskConnection.objIn;
            if (result == null){
                System.out.println("result at getStatistics() is null");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occured at getStatistics() try block");
        }
        Statistics failedStats = new Statistics();
        return failedStats;
    }

    public static Statistics getStatsConnection(String username) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Statistics statistics = restTemplate.getForObject(
                UrlHolder.getUrl() + "request/statistics/" + username,
                Statistics.class);
        System.out.println("statistics object returned at getStatsConnecion() is: " + statistics.toString());
        return statistics;
    }

    public VegetarianMeal getVegetarianMeal(String username) {
        try{
            AsyncTaskConnection taskConnection = new AsyncTaskConnection();
            ArrayList array = new ArrayList(1);
            array.add(0, username);
            taskConnection.execute(array);
            VegetarianMeal result =  (VegetarianMeal) taskConnection.objIn;
            return result;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error occured at getVegetarianMeal() try block");
        }
        VegetarianMeal failedVeggie = new VegetarianMeal();
        return failedVeggie;
    }
    private static VegetarianMeal getVeggieConnection(String username) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        VegetarianMeal vegMeal = restTemplate.getForObject(
                UrlHolder.getUrl() + "request/vegetarianmeal/" + username,
                VegetarianMeal.class
        );
        return vegMeal;
    }


    class AsyncTaskConnection extends AsyncTask<ArrayList, Void, Object>{

        Object objIn;
        String username;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //TODO Display loading icon or something
        }

        @Override
        protected Object doInBackground(ArrayList... arrayLists) {
            username = (String) arrayLists[0].get(0);
            System.out.println(objIn);
            if (objIn instanceof Statistics) {
                try {
                    objIn = (Statistics) getStatsConnection(username);
                    System.out.println("objIn at GetRequests.java is: " + objIn);
                    return objIn;
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("IO Error at getStatsConnection()");
                }
            } else if(objIn instanceof VegetarianMeal){
                try {
                    objIn = (VegetarianMeal) getVeggieConnection(username);
                    System.out.println("objIn at GetRequests.java is: " + objIn);
                    return objIn;
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("IO Error at getVeggieConnection()");
                }
            }
            return objIn;
        }

//        @Override
//        protected Statistics onPostExecute(Statistics statistics) {
//            super.onPostExecute(statistics);
//            return stats;
//        }
    }
}
