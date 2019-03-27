package models;

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
    @Getter @Setter int dailyTravelDistanceByCar;
    @Getter @Setter int totalTravelDistanceByCar;
    @Getter @Setter boolean dailyDistanceReached;
    @Getter @Setter int totalTravelDistanceByBike;
    @Getter @Setter int totalTravelDistanceByPublicTransport;

    // Vegetarian meal attributes
    @Getter @Setter int numberOfVegetarianMeals;

    // CO2 saved per feature
    @Getter @Setter int reducedEmissionByVegetarianMeal;
    @Getter @Setter int reducedEmissionByTravelingByBike;
    @Getter @Setter int reducedEmissionByTravelingByPublicTransport;

}
