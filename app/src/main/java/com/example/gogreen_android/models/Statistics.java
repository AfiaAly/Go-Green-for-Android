package com.example.gogreen_android.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class Statistics {
    @Getter @Setter String username;
    @Getter @Setter int score;
    @Getter @Setter int totalReducedEmission;

    // Travel attributes
    @Getter @Setter int usualTravelDistanceByCar;
    @Getter @Setter int totalTravelDistanceByCar;
    @Getter @Setter int dailyDistanceLeft;
    @Getter @Setter boolean dailyDistanceReached;
    @Getter @Setter int totalTravelDistanceByBike;
    @Getter @Setter int totalTravelDistanceByPublicTransport;

    // Vegetarian meal attributes
    @Getter @Setter int numberOfVegetarianMeals;
    @Getter @Setter int numberOfLocalProduce;
    @Getter @Setter int veggieMealsStreakNumber;

    // CO2 saved per feature
    @Getter @Setter int reducedEmissionByVegetarianMeal;
    @Getter @Setter int reducedEmissionByTravelingByBike;
    @Getter @Setter int reducedEmissionByTravelingByPublicTransport;
    @Getter @Setter int reducedEmissionByBuyingLocalProducts;
    @Getter @Setter int reducedEmissionBySolarPanels;
    @Getter @Setter int reducedEmissionByRoomTemperature;
    @Getter @Setter int bikeStreakNumber;

    // boolean for lower Temperature feature button
    @Getter @Setter boolean disableLowerTemperatureFeature;
}
