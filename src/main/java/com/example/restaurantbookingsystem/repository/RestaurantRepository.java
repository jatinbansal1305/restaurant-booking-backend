package com.example.restaurantbookingsystem.repository;

import com.example.restaurantbookingsystem.model.DateSlot;
import com.example.restaurantbookingsystem.model.Restaurant;
import com.example.restaurantbookingsystem.model.TimeSlot;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantRepository {
    private static RestaurantRepository instance;
    private List<Restaurant> restaurants;

    private RestaurantRepository() {
        restaurants = new ArrayList<>();
    }

    public static synchronized RestaurantRepository getInstance() {
        if (instance == null) {
            instance = new RestaurantRepository();
        }
        return instance;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurants;
    }

    public Restaurant findById(String id) {
        return restaurants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void addSlotsToRestaurant(String restaurantId, List<DateSlot> slots) {
        Restaurant restaurant = findById(restaurantId);

        if (restaurant != null) {
            int totalTablesAvailable = restaurant.getTables().size();
            System.out.println("printing size(): " + slots.size());

            slots.forEach(slot -> {
                slot.getTimeSlots().forEach(timeSlot -> {
                    timeSlot.setNumberOfTables(totalTablesAvailable);
                });
            });

            System.out.println("printing size(): " + slots.size());

            restaurant.getDateSlots().addAll(slots);
        }
    }

    public void updateRestaurant(String entityId, List<TimeSlot> availableSlots) {

    }
}
