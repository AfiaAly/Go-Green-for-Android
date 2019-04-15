package com.example.gogreen_android.requests;

import com.example.gogreen_android.models.Statistics;
import com.example.gogreen_android.models.VegetarianMeal;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class GetRequests {

    public static Statistics getStatsConnection(String username) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        Statistics statistics = restTemplate.getForObject(
                UrlHolder.getUrl() + "request/statistics/" + username,
                Statistics.class);
        System.out.println("statistics object returned at getStatsConnecion() is: " + statistics.toString());
        return statistics;
    }

    protected static VegetarianMeal getVeggieConnection(String username) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        VegetarianMeal vegMeal = restTemplate.getForObject(
                UrlHolder.getUrl() + "request/vegetarianmeal/" + username,
                VegetarianMeal.class
        );
        return vegMeal;
    }
}
