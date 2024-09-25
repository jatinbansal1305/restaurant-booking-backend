package com.example.restaurantbookingsystem.service;


import com.example.restaurantbookingsystem.model.*;
import com.example.restaurantbookingsystem.repository.RestaurantRepository;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RestaurantManager implements HospitalityService<Restaurant> {

    private final RestaurantRepository restaurantRepository ;

    public RestaurantManager(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public void register(Restaurant restaurant) {
        restaurantRepository.addRestaurant(restaurant);
    }

    @Override
    public void addSlots(String restaurantId, List<DateSlot> slots) {
        restaurantRepository.addSlotsToRestaurant(restaurantId, slots);
    }

    @Override
    public List<Restaurant> search(Map<String, String> criteria) {
        List<Restaurant> restaurants = restaurantRepository.getAllRestaurants();

        return restaurants.stream()
                .filter(r -> matchesCriteria(r, criteria))
                .collect(Collectors.toList());
    }

    public List<TimeSlot> getAvailableSlotsForDate(String restaurantId, LocalDate date) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId);

        List<DateSlot> dateSlots = restaurant.getDateSlots();


        List<TimeSlot> availableSlots = dateSlots.stream()
                .filter(dateSlot -> dateSlot.getDate().equals(date))
                .flatMap(dateSlot -> dateSlot.getTimeSlots().stream())
                .filter(slot->!slot.isBooked())
                .collect(Collectors.toList());




        return availableSlots;
    }

    private boolean matchesCriteria(Restaurant restaurant, Map<String, String> criteria) {

        if (criteria.containsKey("name") &&
                criteria.get("name") != null &&
                !criteria.get("name").isEmpty() &&
                !restaurant.getName().toLowerCase().contains(criteria.get("name").toLowerCase())) {
            return false;
        }

        if (criteria.containsKey("city") &&
                criteria.get("city") != null &&
                !criteria.get("city").isEmpty() &&
                !restaurant.getAddress().getCity().equalsIgnoreCase(criteria.get("city"))) {
            return false;
        }

        if (criteria.containsKey("type") &&
                criteria.get("type") != null &&
                !criteria.get("type").isEmpty() &&
                !restaurant.getType().name().equalsIgnoreCase(criteria.get("type"))) {
            return false;
        }

        if (criteria.containsKey("rating") &&
                criteria.get("rating") != null &&
                !criteria.get("rating").isEmpty()) {

            double ratingCriteria = Double.parseDouble(criteria.get("rating"));
            if (restaurant.getRating() < ratingCriteria) {
                return false;
            }
        }

        return true;
    }
}
