package com.example.restaurantbookingsystem.model;


import java.time.LocalDate;

public interface Booking {
    String getBookingId();
    String getEntityId();
    void setEntityId(String id);
    String getCustomerId();
    LocalDate getBookingDate();
    String getBookingTime();
    int getNumberOfGuests();
    boolean isConfirmed();
}
