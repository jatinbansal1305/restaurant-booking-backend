package com.example.restaurantbookingsystem.model;

import java.time.LocalDate;

public class RestaurantBooking extends AbstractBooking{

    public RestaurantBooking(String bookingId, String restaurantId, String customerId,
                             LocalDate bookingDate, String bookingTime, int numberOfGuests) {
        super(bookingId, restaurantId, customerId, bookingDate, bookingTime, numberOfGuests);
    }

}
