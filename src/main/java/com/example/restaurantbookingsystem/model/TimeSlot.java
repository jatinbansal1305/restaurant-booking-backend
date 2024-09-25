package com.example.restaurantbookingsystem.model;

import java.time.LocalTime;

public class TimeSlot {
    private String time;
    private boolean isBooked;

    private int numberOfTables;

    public TimeSlot(String time) {
        this.time = time;
        this.isBooked = false;
        this.numberOfTables = 0 ;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookSlot() {
        this.isBooked = true;
    }

    public void cancelBooking() {
        this.isBooked = false;
    }

    public int getNumberOfTables() {
        return numberOfTables;
    }

    public void setNumberOfTables(int numberOfTables) {
        if(numberOfTables == 0) {
            isBooked = true;
        }
        this.numberOfTables = numberOfTables;
    }
}
