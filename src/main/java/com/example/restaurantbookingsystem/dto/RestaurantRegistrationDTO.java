package com.example.restaurantbookingsystem.dto;

import com.example.restaurantbookingsystem.model.Address;
import com.example.restaurantbookingsystem.model.Cuisine;
import com.example.restaurantbookingsystem.model.DateSlot;
import com.example.restaurantbookingsystem.model.Table;
import com.example.restaurantbookingsystem.model.enums.FoodType;

import java.util.ArrayList;
import java.util.List;

public class RestaurantRegistrationDTO {
    private String name;
    private Address address;
    private List<Cuisine> cuisines;
    private double rating;
    private List<DateSlot> dateSlots;
    private FoodType type;
    private int costForTwo;
    private List<Table> tables;

    public RestaurantRegistrationDTO(String name, Address address, List<Cuisine> cuisines, double rating, FoodType type, int costForTwo, List<Table> tables) {
        this.name = name;
        this.address = address;
        this.cuisines = cuisines;
        this.rating = rating;
        this.dateSlots = new ArrayList<>();
        this.type = type;
        this.costForTwo = costForTwo;
        this.tables = tables;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<DateSlot> getDateSlots() {
        return dateSlots;
    }

    public void setSlots(List<DateSlot> dateSlots) {
        this.dateSlots = dateSlots;
    }

    public FoodType getType() {
        return type;
    }

    public void setType(FoodType type) {
        this.type = type;
    }

    public int getCostForTwo() {
        return costForTwo;
    }

    public void setCostForTwo(int costForTwo) {
        this.costForTwo = costForTwo;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }
}

