package com.example.gogreen_android.models;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Travel {
    @Getter @Setter private String username;
    @Getter @Setter private int usualTravelDistancePerDay;
    @Getter @Setter private int travelDistanceByBike;
    @Getter @Setter private int travelDistanceByPublicTransport;
}
