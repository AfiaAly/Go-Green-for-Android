package com.example.gogreen_android.models;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ProfileTest {

    Profile profile = new Profile("user4", "Netherlands", "60", 28, 48,
            183, 150, 27,
            4, 9, "fa4b");

    @Test
    public void constructorNotNullTest() {
        assertNotNull(profile);
    }

    @org.junit.jupiter.api.Test
    public void allArgsConstructorTest() {
        Profile profile = new Profile("user4", "Netherlands", "60", 28, 48,
                183, 150, 27,
                4, 9, "fa4b");
        assertNotNull(profile);
        assertEquals("user4", profile.getUsername());
        assertEquals("Netherlands", profile.getCountry());
        assertEquals("60", profile.getDistance());
        assertEquals(28, profile.getIncome());
        assertEquals(48, profile.getCarEfficiency());
        assertEquals(183, profile.getElectricitybill());
        assertEquals(150, profile.getHeatingBill());
        assertEquals(27, profile.getRoomTemperature());
        assertEquals(4, profile.getRoomTemperatureLoweredBy());
        assertEquals(9, profile.getGreenEnergy());
        assertEquals("fa4b", profile.getUserId());
    }

    @Test
    public void setUsernameTest() {
        profile.setUsername("user3");
        assertEquals("user3", profile.getUsername());
    }

    @Test
    public void setCountryTest() {
        profile.setCountry("Egypt");
        assertEquals("Egypt", profile.getCountry());
    }

    @Test
    public void setDistanceTest() {
        profile.setDistance("45");
        assertEquals("45", profile.getDistance());
    }

    @Test
    public void setIncomeTest() {
        profile.setIncome(35);
        assertEquals(35, profile.getIncome());
    }

    @Test
    public void setCarEfficiencyTest() {
        profile.setCarEfficiency(90);
        assertEquals(90, profile.getCarEfficiency());
    }

    @Test
    public void setElectricitybillTest() {
        profile.setElectricitybill(150);
        assertEquals(150, profile.getElectricitybill());
    }

    @Test
    public void setHeatingBillTest() {
        profile.setHeatingBill(90);
        assertEquals(90, profile.getHeatingBill());
    }

    @Test
    public void setRoomTemperatureTest() {
        profile.setRoomTemperature(20);
        assertEquals(20, profile.getRoomTemperature());
    }

    @Test
    public void setRoomTemperatureLoweredByTest() {
        profile.setRoomTemperatureLoweredBy(8);
        assertEquals(8, profile.getRoomTemperatureLoweredBy());
    }

    @Test
    public void setGreenEnergyTest() {
        profile.setGreenEnergy(15);
        assertEquals(15, profile.getGreenEnergy());
    }

    @Test
    public void setUserIdTest() {
        profile.setUserId("foo");
        assertEquals("foo", profile.getUserId());
        System.out.println(profile.toString());
    }

    @Test
    public void toStringTest() {
        assertEquals("Profile(username=" + profile.getUsername() + ", country=" + profile.getCountry() + ", distance=" +
                profile.getDistance() + ", income=" + profile.getIncome() + ", carEfficiency=" + profile.getCarEfficiency() +
                ", electricitybill=" + profile.getElectricitybill() +
                ", heatingBill=" + profile.getHeatingBill() + ", roomTemperature=" + profile.getRoomTemperature() +
                ", roomTemperatureLoweredBy=" + profile.getRoomTemperatureLoweredBy() + ", greenEnergy=" +
                profile.getGreenEnergy() + ", userId=" + profile.getUserId() + ")", profile.toString());
    }
}