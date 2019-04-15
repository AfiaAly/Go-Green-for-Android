package com.example.gogreen_android.requests;

import android.os.AsyncTask;

import com.example.gogreen_android.models.Profile;
import com.example.gogreen_android.models.User;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;


public class UserRequests {

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
                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
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
}
