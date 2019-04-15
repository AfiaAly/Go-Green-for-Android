package com.example.gogreen_android.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString

public class SolarPanels {
    @Getter @Setter private Boolean solarpanel;
    @Getter @Setter private LocalDate date;
}
