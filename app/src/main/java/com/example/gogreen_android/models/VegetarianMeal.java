package com.example.gogreen_android.models;

import lombok.*;

// This class is used to send the message to up the veggie from server and back
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class VegetarianMeal {

    // This string will be used to tell the database who to update the count for
    @Getter @Setter private String username;

    // This gives the amount of veggie meals eaten by the user.
    @Getter @Setter private int numberOfMeals;

    // This value has already been set since it will reduce the emissions by a be constant
    @Getter @Setter private int reducedEmission = 5;
}
