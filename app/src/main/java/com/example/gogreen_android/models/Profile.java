package com.example.gogreen_android.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Profile {

    @Getter @Setter private String username;
    @Getter @Setter private String country;
    @Getter @Setter private String distance;
    @Getter @Setter private int income;
    @Getter @Setter private int carEfficiency;
    @Getter @Setter private int electricitybill;
    @Getter @Setter private int heatingBill;
    @Getter @Setter private int roomTemperature;
    @Getter @Setter private int roomTemperatureLoweredBy;
    @Getter @Setter private int greenEnergy;
    @Getter @Setter private String userId;
}