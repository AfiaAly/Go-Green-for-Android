package com.example.gogreen_android.requests;

//import org.springframework.web.client.RestTemplate;

//import com.google.gson.Gson;

import android.os.AsyncTask;

import com.example.gogreen_android.models.Profile;
import com.example.gogreen_android.models.SolarPanels;
import com.example.gogreen_android.models.User;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;


public class UserRequests {

    /**
     * Sends a POST request with User object with username and password.
     *
     * @param username username
     * @param password password
     * @return User object with success flag set to true or false
     */
    public static User loginPostRequest(String username, String password) throws IOException {
        try {
            AsyncTaskConnection taskConnection = new AsyncTaskConnection();
            ArrayList array = new ArrayList(2);
            array.add(0, username);
            array.add(1, password);
            taskConnection.execute(array);
            System.out.println("Connection executed");
            User result = (User) taskConnection.get();
            System.out.println("...." + result);
            return result;
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Error occured at loginPostRequest() method");
        }
        User failedUser = new User("0000", "0000", false);
        return failedUser;
    }

    public static User returnUser(User user){
        return user;
    }

    public static Profile returnProfile(Profile profile){
        return profile;
    }

    public static User loginRequestConnection(String username, String password) throws IOException {
        User user = new User(username, password, false);
        System.out.println(username + password);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        user = restTemplate.postForObject( UrlHolder.getUrl() + "login", user, User.class);
        System.out.println("Result from loginRequestConnection:" + user);
        return user;
    }

    /**
     * This method retrieves the user's basecase.
     * @param username is used to identify the current user.
     * @return returns the user containing the base case.
     */
    public static Profile getProfilepostRequest(String username) {
        try {
            AsyncTaskConnection taskConnection = new AsyncTaskConnection();
            ArrayList array = new ArrayList(1);
            array.add(0, username);
            taskConnection.execute(array);
            Profile result = (Profile) taskConnection.objOut;
            System.out.println("profile object return at getProfilepostRequest() is: " + result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error occured at getProfilepostRequest() method");
        }
        Profile failedProfile = new Profile();
        return failedProfile;
    }

    public static Profile getProfileConnection(String username) throws IOException {
        Profile profile = new Profile();
        profile.setUsername(username);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        profile = restTemplate.postForObject(UrlHolder.getUrl() + "get/profile", profile, Profile.class);
        System.out.println("profile object at getProfileConnection() is:" + profile);
        return profile;
    }

    static class AsyncTaskConnection extends AsyncTask<ArrayList, Void, Object>{ //.................................................

        Object objOut;
        String username;
        String password;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(ArrayList... arrayLists) {
            if (arrayLists[0].size() == 1){
                try {
                    System.out.println("Array acquired has length 1. Executing method : getProfileConnection(username)");
                    username = (String) arrayLists[0].get(0);
                    objOut = (Profile) getProfileConnection(username);
                    System.out.println("objOut at UserRequests.java is: " + objOut);
                    return objOut;
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("IO Error occured at AsyncTask while calling getProfileConnection()");
                }
            } else if (arrayLists[0].size() == 2){
                try {
                    System.out.println("Array acquired has length 2. Executing method loginRequestConnection()");
                    username = (String) arrayLists[0].get(0);
                    password = (String) arrayLists[0].get(1);
                    objOut = (User) loginRequestConnection(username, password);
                    System.out.println("objOut at UserRequests.java is: " + objOut);
                    return objOut;
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("IO Error occured at AsyncTask while calling loginRequestConnection()");
                }
            } else {
                System.out.println("Something wrong with arrays passed to AsyncTask in UserRequests.java");
            }
            Object failed = new Object();
            return failed;
        }

        @Override
        protected void onPostExecute(Object o) { //.................................................................................
            super.onPostExecute(o);
            if (o instanceof User){
                User user = (User) o;
                returnUser(user);
            } else if (o instanceof Profile){
                Profile profile = (Profile) o;
                returnProfile(profile);
            }
        }
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
        }
    }
}
