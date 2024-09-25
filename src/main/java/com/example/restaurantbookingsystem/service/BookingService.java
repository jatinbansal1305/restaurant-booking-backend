package com.example.restaurantbookingsystem.service;

public interface BookingService<T> {
    void makeBooking(T booking) throws Exception;
    void cancelBooking(String bookingId);
    T getBookingDetails(String bookingId);
}
