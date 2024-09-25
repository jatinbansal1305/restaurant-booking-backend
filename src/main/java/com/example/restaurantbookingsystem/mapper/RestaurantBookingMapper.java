package com.example.restaurantbookingsystem.mapper;


import com.example.restaurantbookingsystem.dto.BookingRequestDTO;
import com.example.restaurantbookingsystem.model.RestaurantBooking;

import java.time.LocalDate;

public class RestaurantBookingMapper {

    public static RestaurantBooking toRestaurantBooking(BookingRequestDTO request) {
        LocalDate bookingDate = request.getDate();
        String bookingTime = request.getTimeSlot();

        String bookingId = generateBookingId();

        return new RestaurantBooking(bookingId, request.getRestaurantId(), "User", bookingDate, bookingTime, request.getNumberOfPeople());
    }

    private static String generateBookingId() {
        return "BOOKING_" + System.currentTimeMillis();
    }
}
