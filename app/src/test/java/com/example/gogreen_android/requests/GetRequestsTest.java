package com.example.gogreen_android.requests;

import com.example.gogreen_android.models.Statistics;
import com.example.gogreen_android.models.VegetarianMeal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static com.example.gogreen_android.requests.GetRequests.getStatsConnection;
import static com.example.gogreen_android.requests.GetRequests.getVeggieConnection;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


public class GetRequestsTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Test
    public void getStatsConnectionNotNullTest() {
        try{
            assertNotNull(getStatsConnection("user4"));
        } catch (IOException e) {
            System.out.println("IO Error at getStatsConnectionNotNullTest()");
        }
    }

    @Test
    public void getStatsConnectionRightTypeTest() {
        try{
            assertTrue(getStatsConnection("user4") instanceof Statistics);
        } catch (IOException e) {
            System.out.println("IO Error at getStatsConnectionRightTypeTest()");
        }
    }

    @Test
    public void getStatsConnectionThrowsExceptionTest() {
        exception.expect(Exception.class);
        try {
            getStatsConnection("userr4");
        } catch (IOException e) {
            System.out.println("IO Error at getStatsConnectionThrowsExceptionTest()");
        }
    }



    @Test
    public void getVeggieConnectionNotNullTest() {
        try{
            assertNotNull(getVeggieConnection("user4"));
        } catch (IOException e) {
            System.out.println("IO Error at getVeggieConnectionNotNullTest()");
        }
    }

    @Test
    public void getVeggieConnectionRightTypeTest() {
        try{
            assertTrue(getVeggieConnection("user4") instanceof VegetarianMeal);
        } catch (IOException e) {
            System.out.println("IO Error at getVeggieConnectionRightTypeTest()");
        }
    }

    @Test
    public void getVeggieConnectionThrowsExceptionTest() {
        exception.expect(Exception.class);
        try {
            getStatsConnection("userr4");
        } catch (IOException e) {
            System.out.println("IO Error at getStatsConnectionThrowsExceptionTest()");
        }
    }
}