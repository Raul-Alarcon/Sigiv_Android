package com.example.planme.ui.models;

import java.util.Date;

public class FlightUI {
    private Date time;
    private AirportUI departure;
    private AirportUI destination;

    public FlightUI(Date time, AirportUI departure, AirportUI destination, int color) {
        this.time = time;
        this.departure = departure;
        this.destination = destination;
        this.color = color;
    }

    private int color;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
