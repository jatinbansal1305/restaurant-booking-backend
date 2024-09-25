package com.example.restaurantbookingsystem.dto;

import java.time.LocalDate;

public class BookingRequestDTO {
    private String restaurantId;
    private int numberOfPeople;
    private String timeSlot;
    private LocalDate date;

    public BookingRequestDTO(String restaurantId, int numberOfPeople, String timeSlot, LocalDate date) {
        this.restaurantId = restaurantId;
        this.numberOfPeople = numberOfPeople;
        this.timeSlot = timeSlot;
        this.date = date;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
