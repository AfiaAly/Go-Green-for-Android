package com.example.gogreen_android.requests;

import com.example.gogreen_android.models.Profile;
import com.example.gogreen_android.models.User;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.gogreen_android.requests.UserRequests.getProfileConnection;
import static com.example.gogreen_android.requests.UserRequests.loginRequestConnection;
import static com.example.gogreen_android.requests.UserRequests.returnProfile;
import static com.example.gogreen_android.requests.UserRequests.returnUser;
import static org.junit.Assert.*;


public class UserRequestsTest {

//    @Test
//    public void loginPostRequestTest() {
//        User user = loginPostRequest("user4", "pwd");
//        assertTrue(user.isSuccess());
//    }
//
//    @Test
//    public void failedLoginPostRequestTest() {
//        User user = loginPostRequest("user4", "pwdd");
//        assertFalse(user.isSuccess());
//    }

    @Test
    public void returnUserTest() {
        User user = new User();
        assertEquals(user, returnUser(user));
    }

    @Test
    public void returnProfileTest() {
        Profile profile = new Profile();
        assertEquals(profile, returnProfile(profile));
    }

    @Test
    public void loginRequestConnectionTest() {
        try {
            User user = loginRequestConnection("user4", "pwd");
            assertTrue(user.isSuccess());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO Error at loginRequestConnectionTest()");
        }
    }

    @Test
    public void failedLoginRequestConnectionTest() {
        try {
            User user = loginRequestConnection("user4", "pwdd");
            assertFalse(user.isSuccess());
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO Error at loginRequestConnectionTest()");
        }
    }

    @Test
    public void getProfileConnectionTest() {
        try {
            Profile profile = getProfileConnection("user4");
            assertTrue(profile != null);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("IO Error at getProfileConnectionTest()");
        }
    }

    @Test
    public void aSyncTaskProfileTest(){
        UserRequests.AsyncTaskConnection taskConnection = new UserRequests.AsyncTaskConnection();
        ArrayList array = new ArrayList(1);
        array.add(0, "user4");
        Object result = taskConnection.doInBackground(array);
        assertTrue(result instanceof Profile);
    }

    @Test
    public void aSyncTaskUserTest(){
        UserRequests.AsyncTaskConnection taskConnection = new UserRequests.AsyncTaskConnection();
        ArrayList array = new ArrayList(2);
        array.add(0, "user4");
        array.add(1, "pwd");
        Object result = taskConnection.doInBackground(array);
        assertTrue(result instanceof User);
    }

    @Test
    public void aSyncTaskTestEmptyArray(){
        UserRequests.AsyncTaskConnection taskConnection = new UserRequests.AsyncTaskConnection();
        ArrayList array = new ArrayList(0);
        Object result = taskConnection.doInBackground(array);
        assertNull(result);
    }

    @Test
    public void aSyncTaskTestLargeArray(){
        UserRequests.AsyncTaskConnection taskConnection = new UserRequests.AsyncTaskConnection();
        ArrayList array = new ArrayList(3);
        Object result = taskConnection.doInBackground(array);
        assertNull(result);
    }
}