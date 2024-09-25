package com.example.restaurantbookingsystem.model;

import java.time.LocalDate;

public abstract class AbstractBooking implements Booking {
    private String bookingId;
    private String entityId;
    private String customerId;
    private LocalDate bookingDate;
    private String bookingTime;
    private int numberOfGuests;
    private boolean isConfirmed;

    protected AbstractBooking(String bookingId, String entityId, String customerId,
                              LocalDate bookingDate, String bookingTime, int numberOfGuests) {
        this.bookingId = bookingId;
        this.entityId = entityId;
        this.customerId = customerId;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.numberOfGuests = numberOfGuests;
        this.isConfirmed = false;
    }

    @Override
    public String getBookingId() {
        return bookingId;
    }

    @Override
    public String getEntityId() {
        return entityId;
    }

    @Override
    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }


    @Override
    public String getCustomerId() {
        return customerId;
    }


    @Override
    public LocalDate getBookingDate() {
        return bookingDate;
    }

    @Override
    public String getBookingTime() {
        return bookingTime;
    }

    @Override
    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    @Override
    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }
}