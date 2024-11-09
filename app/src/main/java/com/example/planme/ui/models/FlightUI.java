package com.example.planme.ui.models;

import java.time.LocalDateTime;

public class FlightUI {
    private LocalDateTime time;
    private AirportUI departure;
    private AirportUI destination;

    public FlightUI(LocalDateTime time, AirportUI departure, AirportUI destination, int color) {
        this.time = time;
        this.departure = departure;
        this.destination = destination;
        this.color = color;
    }

    private int color;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public AirportUI getDeparture() {
        return departure;
    }

    public void setDeparture(AirportUI departure) {
        this.departure = departure;
    }

    public AirportUI getDestination() {
        return destination;
    }

    public void setDestination(AirportUI destination) {
        this.destination = destination;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
