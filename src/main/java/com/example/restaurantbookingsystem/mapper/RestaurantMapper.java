package com.example.restaurantbookingsystem.mapper;

import com.example.restaurantbookingsystem.dto.RestaurantRegistrationDTO;
import com.example.restaurantbookingsystem.model.Restaurant;

public class RestaurantMapper {

    public static Restaurant mapToRestaurant(RestaurantRegistrationDTO registrationDTO) {
        String id = registrationDTO.getName();

        return new Restaurant(
                id,
                registrationDTO.getName(),
                registrationDTO.getAddress(),
                registrationDTO.getCuisines(),
                registrationDTO.getRating(),
                registrationDTO.getDateSlots(),
                registrationDTO.getType(),
                registrationDTO.getCostForTwo(),
                registrationDTO.getTables()
        );
    }

}
