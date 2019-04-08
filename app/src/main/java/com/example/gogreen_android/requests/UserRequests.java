package com.example.gogreen_android.requests;

//import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
    public static User loginPostRequest(String username, String password) throws IOException {
        User user = new User(username, password, false);
        HttpURLConnection client = null;
        try{

            //Open URL connection
            URL url = new URL("http://10.0.2.2/login");
            client = (HttpURLConnection) url.openConnection();
            client.setRequestMethod("POST");
            client.setDoOutput(true);
            client.setDoInput(true);

            //Send object
            ObjectOutputStream objOut = new ObjectOutputStream(client.getOutputStream());
            objOut.writeObject(user);
            objOut.flush();
            objOut.close();

            //Receive object
            ObjectInputStream objIn = new ObjectInputStream(client.getInputStream());
            user = (User) objIn.readObject();
            objIn.close();
        } catch (IOException e){
            e.printStackTrace();
//            MainActivity.connectionError();
        } catch (ClassNotFoundException e){
            e.printStackTrace();
            System.out.println("ERROR : Class not found");
        }

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
        HttpURLConnection client = null;
        try{

            //Open URL connection
            URL url = new URL("http://localhost:8080/signup");
            client = (HttpURLConnection) url.openConnection();
            client.setRequestMethod("POST");
            client.setDoOutput(true);

            //Send object
            ObjectOutputStream objOut = new ObjectOutputStream(client.getOutputStream());
            objOut.writeObject(user);
            objOut.flush();
            objOut.close();
        } catch (IOException e){
            e.printStackTrace();
//            MainActivity.connectionError(); //TODO change loginMessage in MainActivity to show error
        }
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

        HttpURLConnection client = null;
        try{

            //Open URL connection
            URL url = new URL("http://localhost:8080/get/profile");
            client = (HttpURLConnection) url.openConnection();
            client.setRequestMethod("POST");
            client.setDoOutput(true);

            //Send object
            ObjectOutputStream objOut = new ObjectOutputStream(client.getOutputStream());
            objOut.writeObject(profile);
            objOut.flush();
            objOut.close();
        } catch (IOException e){
            e.printStackTrace();
//            MainActivity.connectionError(); //Method is non-static... What?
        }
        return profile;
    }
//        RestTemplate restTemplate = new RestTemplate();
//        profile = restTemplate.postForObject("http://localhost:8080/get/profile", profile, Profile.class);

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

         HttpURLConnection client = null;
         try{

             //Open URL connection
             URL url = new URL("http://localhost:8080/send/profile");
             client = (HttpURLConnection) url.openConnection();
             client.setRequestMethod("POST");
             client.setDoOutput(true);

             //Send object
             ObjectOutputStream objOut = new ObjectOutputStream(client.getOutputStream());
             objOut.writeObject(profile);
             objOut.flush();
             objOut.close();
         } catch (IOException e){
             e.printStackTrace();
//            MainActivity.connectionError(); //Method is non-static... What?
         }
         return profile;

//        RestTemplate restTemplate = new RestTemplate();
//        profile = restTemplate.postForObject("http://localhost:8080/send/profile", profile, Profile.class);
//        return profile;
    }

    /**
     * This method sends the existence of solarPanels.
     * @param Username their userName identifies the current user.
     * @param solarpanel checks if the user actually has solar panels.
     * TODO Does this use a POST or GET or PUT HTTP request?
     */
    public static void sendSolarPanels(String Username, boolean solarpanel){
        LocalDate today = LocalDate.now();
        System.out.println(today);
        SolarPanels solarpanels = new SolarPanels(solarpanel, today);

        HttpURLConnection client = null;
        try{

            //Open URL connection
            URL url = new URL("http://localhost:8080/send/solar");
            client = (HttpURLConnection) url.openConnection();
            client.setRequestMethod("POST");
            client.setDoOutput(true);

            //Send object
            ObjectOutputStream objOut = new ObjectOutputStream(client.getOutputStream());
            objOut.writeObject(solarpanels);
            objOut.flush();
            objOut.close();
        } catch (IOException e){
            e.printStackTrace();
//            MainActivity.connectionError(); //Method is non-static... What?
        }
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.put("http://localhost:8080/send/solar", solarpanels);
    }
}
