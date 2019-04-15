package com.example.gogreen_android.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class VegetarianMealTest {

    VegetarianMeal vegetarianMeal;

    @BeforeEach
    void setUp() {
        vegetarianMeal = new VegetarianMeal();
    }

    @Test
    void emptyConstructorTest() {
        vegetarianMeal = new VegetarianMeal();
        assertNotNull(vegetarianMeal);
    }

    @Test
    void allArgumentsConstructorTest() {
        vegetarianMeal = new VegetarianMeal("Klaas", 5, 2);
        assertNotNull(vegetarianMeal);
        assertEquals("Klaas", vegetarianMeal.getUsername());
        assertEquals(5, vegetarianMeal.getNumberOfMeals());
        assertEquals(2, vegetarianMeal.getReducedEmission());
    }

    @Test
    void setNumberOfMealsTest() {
        vegetarianMeal.setNumberOfMeals(5);
        assertEquals(5, vegetarianMeal.getNumberOfMeals());
    }

    @Test
    void getNumberOfMealsAmountTest() {
        vegetarianMeal.setNumberOfMeals(42);
        assertEquals(42, vegetarianMeal.getNumberOfMeals());

    }

    @Test
    void setUsernameTest() {
        vegetarianMeal.setUsername("John Doe");
        assertEquals("John Doe", vegetarianMeal.getUsername());
    }

    @Test
    void getUsernameTest() {
        vegetarianMeal.setUsername("John Doe");
        assertEquals("John Doe", vegetarianMeal.getUsername());

    }

    @Test
    void setReducedEmissionTest() {
        vegetarianMeal.setReducedEmission(500);
        assertEquals(500, vegetarianMeal.getReducedEmission());

    }

    @Test
    void getReducedEmissionTest() {
        vegetarianMeal.setReducedEmission(420);
        assertEquals(420, vegetarianMeal.getReducedEmission());
    }
}