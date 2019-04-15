package com.example.gogreen_android.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StatisticsTest {

    Statistics stats;

    @BeforeEach
    public void setUp() throws Exception {
        stats = new Statistics("User", 1,
                2, 3,
                4, 5,
                false, 6,
                7, 8, 12,
                9,100,  10,
                11, 14,
                15, 16,
                17, false);
    }

    @Test
    public void setUsername() {
        stats.setUsername("newUser");
        assertEquals("newUser", stats.getUsername());
    }

    @Test
    public void setScore() {
        stats.setScore(42);
        assertEquals(42, stats.getScore());
    }

    @Test
    public void setTotalReducedEmission() {
        stats.setTotalReducedEmission(42);
        assertEquals(42, stats.getTotalReducedEmission());
    }

    @Test
    public void setUsualTravelDistanceByCar() {
        stats.setUsualTravelDistanceByCar(42);
        assertEquals(42, stats.getUsualTravelDistanceByCar());
    }

    @Test
    public void setTotalTravelDistanceByCar() {
        stats.setTotalTravelDistanceByCar(42);
        assertEquals(42, stats.getTotalTravelDistanceByCar());
    }

    @Test
    public void setDailyDistanceLeft() {
        stats.setDailyDistanceLeft(42);
        assertEquals(42, stats.getDailyDistanceLeft());
    }

    @Test
    public void setDailyDistanceReached() {
        stats.setDailyDistanceReached(true);
        assertTrue(stats.isDailyDistanceReached());
    }

    @Test
    public void setTotalTravelDistanceByBike() {
        stats.setTotalTravelDistanceByBike(42);
        assertEquals(42, stats.getTotalTravelDistanceByBike());
    }

    @Test
    public void setTotalTravelDistanceByPublicTransport() {
        stats.setTotalTravelDistanceByPublicTransport(42);
        assertEquals(42, stats.getTotalTravelDistanceByPublicTransport());
    }

    @Test
    public void setNumberOfVegetarianMeals() {
        stats.setNumberOfVegetarianMeals(42);
        assertEquals(42, stats.getNumberOfVegetarianMeals());
    }

    @Test
    public void setReducedEmissionByVegetarianMeal() {
        stats.setReducedEmissionByVegetarianMeal(42);
        assertEquals(42, stats.getReducedEmissionByVegetarianMeal());
    }

    @Test
    public void setReducedEmissionByTravelingByBike() {
        stats.setReducedEmissionByTravelingByBike(42);
        assertEquals(42, stats.getReducedEmissionByTravelingByBike());
    }

    @Test
    public void setReducedEmissionByTravelingByPublicTransport() {
        stats.setReducedEmissionByTravelingByPublicTransport(42);
        assertEquals(42, stats.getReducedEmissionByTravelingByPublicTransport());
    }

    @Test
    public void setNumberOfLocalProduce() {
        stats.setNumberOfLocalProduce(42);
        assertEquals(42, stats.getNumberOfLocalProduce());
    }

    @Test
    public void setReducedEmissionByBuyingLocalProducts() {
        stats.setReducedEmissionByBuyingLocalProducts(42);
        assertEquals(42, stats.getReducedEmissionByBuyingLocalProducts());
    }

    @Test
    public void setReducedEmissionByLoweringHomeTemperature() {
        stats.setReducedEmissionByRoomTemperature(42);
        assertEquals(42, stats.getReducedEmissionByRoomTemperature());
    }

    @Test
    public void setReducedEmissionBySolarPanels() {
        stats.setReducedEmissionBySolarPanels(42);
        assertEquals(42, stats.getReducedEmissionBySolarPanels());
    }

    @Test
    public void getUsername() {
        assertEquals("User", stats.getUsername());
    }

    @Test
    public void getScore() {
        assertEquals(1, stats.getScore());
    }

    @Test
    public void getTotalReducedEmission() {
        assertEquals(2, stats.getTotalReducedEmission());
    }

    @Test
    public void getUsualTravelDistanceByCar() {
        assertEquals(3, stats.getUsualTravelDistanceByCar());
    }

    @Test
    public void getTotalTravelDistanceByCar() {
        assertEquals(4, stats.getTotalTravelDistanceByCar());
    }

    @Test
    public void getDailyDistanceLeft() {
        assertEquals(5, stats.getDailyDistanceLeft());
    }

    @Test
    public void isDailyDistanceReached() {
        assertFalse(stats.isDailyDistanceReached());
    }

    @Test
    public void getTotalTravelDistanceByBike() {
        assertEquals(6, stats.getTotalTravelDistanceByBike());
    }

    @Test
    public void getTotalTravelDistanceByPublicTransport() {
        assertEquals(7, stats.getTotalTravelDistanceByPublicTransport());
    }

    @Test
    public void getNumberOfVegetarianMeals() {
        assertEquals(8, stats.getNumberOfVegetarianMeals());
    }

    @Test
    public void getReducedEmissionByVegetarianMeal() {
        assertEquals(100, stats.getReducedEmissionByVegetarianMeal());
    }

    @Test
    public void getReducedEmissionByTravelingByBike() {
        assertEquals(10, stats.getReducedEmissionByTravelingByBike());
    }

    @Test
    public void getReducedEmissionByTravelingByPublicTransport() {
        assertEquals(11, stats.getReducedEmissionByTravelingByPublicTransport());
    }

    @Test
    public void getNumberOfLocalProduce() {
        assertEquals(12, stats.getNumberOfLocalProduce());
    }

    @Test
    public void getReducedEmissionByLoweringTemperature() {
        assertEquals(16, stats.getReducedEmissionByRoomTemperature());
    }

    @Test
    public void getReducedEmissionBySolarPanels() {
        assertEquals(15, stats.getReducedEmissionBySolarPanels());
    }

    @Test
    public void getReducedEmissionByBuyingLocalProduce() {
        assertEquals(14, stats.getReducedEmissionByBuyingLocalProducts());
    }
}