package com.example.restaurantbookingsystem.repository;

import com.example.restaurantbookingsystem.model.RestaurantBooking;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RestaurantBookingRepository {
    private static RestaurantBookingRepository instance;
    private Map<String, List<RestaurantBooking>> restaurantBookings;

    private RestaurantBookingRepository() {
        restaurantBookings = new HashMap<>();
    }

    public static synchronized RestaurantBookingRepository getInstance() {
        if (instance == null) {
            instance = new RestaurantBookingRepository();
        }
        return instance;
    }

    public void makeBooking(RestaurantBooking booking) {
        List<RestaurantBooking> bookings = restaurantBookings.getOrDefault(booking.getEntityId(), new ArrayList<>());
        bookings.add(booking);
        restaurantBookings.put(booking.getEntityId(), bookings);
    }

    public List<RestaurantBooking> getBookingsForRestaurant(String restaurantId) {
        return restaurantBookings.getOrDefault(restaurantId, new ArrayList<>());
    }
}
