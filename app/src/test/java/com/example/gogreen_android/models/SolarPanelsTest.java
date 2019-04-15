package com.example.gogreen_android.models;

import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SolarPanelsTest {

    SolarPanels panels = new SolarPanels(true, LocalDate.of(2019, 3, 28));

    @Test
    public void constructorNotNullTest() {
        assertNotNull(panels);
    }

    @Test
    public void allArgsConstructorTest() {
        SolarPanels panels = new SolarPanels(true, LocalDate.of(2018, 3, 28));
        assertNotNull(panels);
        assertEquals(true, panels.getSolarpanel());
        assertEquals(LocalDate.of(2018, 3, 28), panels.getDate());
    }

    @Test
    public void setSolarpanelTest() {
        panels.setSolarpanel(false);
        assertEquals(false, panels.getSolarpanel());
    }

    @Test
    public void setDateTest() {
        panels.setDate(LocalDate.of(1970, 3, 2));
        assertEquals(LocalDate.of(1970, 3, 2), panels.getDate());
    }

    @Test
    public void toStringTest() {
        assertEquals("SolarPanels(solarpanel=" + panels.getSolarpanel() + ", date=" + panels.getDate() + ")", panels.toString());
    }
}