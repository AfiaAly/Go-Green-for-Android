package com.example.gogreen_android.models;

import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString

public class SolarPanels {
    @Getter @Setter private Boolean solarpanel;
    @Getter @Setter private LocalDate date;

    public SolarPanels(Boolean solarpanel, LocalDate date) {
        this.solarpanel = solarpanel;
        this.date = date;
    }
}
