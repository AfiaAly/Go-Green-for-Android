package com.example.gogreen_android.requests;

import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import models.Profile;
import models.SolarPanels;
import models.User;


public class UserRequests {

    /**
     * Sends a POST request with User object with username and password.
     *
     * @param username username
     * @param password password
     * @return User object with success flag set to true or false
     */
    public static User loginPostRequest(String username, String password) {
        User user = new User(username, password, false);
        RestTemplate restTemplate = new RestTemplate();
        user = restTemplate.postForObject("http://localhost:8080/login", user, User.class);
        return user;
    }

    /**
     * Sends a POST request with User object to try to sign a user in.
     *
     * @param username username
     * @param password password
     * @return User object with success flag set to true or false
     */
    public static User signupPostRequest(String username, String password) {
        User user = new User(username, password, false);
        RestTemplate restTemplate = new RestTemplate();
        user = restTemplate.postForObject("http://localhost:8080/signup", user, User.class);
        return user;
    }

    /**
     * This method retrieves the user's basecase.
     * @param username is used to identify the current user.
     * @return returns the user containing the base case.
     */
    public static Profile getProfilepostRequest(String username) {
        Profile profile = new Profile();
        profile.setUsername(username);
        RestTemplate restTemplate = new RestTemplate();
        profile = restTemplate.postForObject("http://localhost:8080/get/profile", profile, Profile.class);
        return profile;
    }

    /**
     * This method sends the base case to the server.
     * @param Username their username identifies the current user.
     * @param income sets the income for the user.
     * @param distance sets the distance parameter for the user.
     * @param country sets the home country for the user.
     * @param carefficiency sets the carEfficiency for the user.
     * @param electricetyBill sets the electricityBill for the user.
     * @return returns proof that the base case was sent.
     */
     public static Profile sendProfilepostRequest(String Username, int income, String distance, String country,
                                                    int carefficiency, int electricetyBill) {
        Profile profile = new Profile(Username, country, distance, income, carefficiency, electricetyBill);
        RestTemplate restTemplate = new RestTemplate();
        profile = restTemplate.postForObject("http://localhost:8080/send/profile", profile, Profile.class);
        return profile;
    }

    /**
     * This method sends the existence of solarPanels.
     * @param Username their userName identifies the current user.
     * @param solarpanel checks if the user actually has solar panels.
     */
    public static void sendSolarPanels(String Username, boolean solarpanel){
        LocalDate today = LocalDate.now();
        System.out.println(today);
        SolarPanels solarpanels = new SolarPanels(solarpanel, today);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put("http://localhost:8080/send/solar", solarpanels);
    }
}
