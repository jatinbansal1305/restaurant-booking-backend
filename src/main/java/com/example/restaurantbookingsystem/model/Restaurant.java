package com.example.restaurantbookingsystem.model;


import com.example.restaurantbookingsystem.model.enums.FoodType;

import java.util.List;


public class Restaurant extends Venue {
    private List<Cuisine> cuisines;
    private double rating;
    private List<DateSlot> dateSlots;
    private FoodType type;
    private int costForTwo;
    private List<Table> tables;

    public Restaurant(String id, String name, Address address, List<Cuisine> cuisines, double rating, List<DateSlot> dateSlots, FoodType type, int costForTwo, List<Table> tables) {
        super(id, name, address);
        this.cuisines = cuisines;
        this.rating = rating;
        this.dateSlots = dateSlots;
        this.type = type;
        this.costForTwo = costForTwo;
        this.tables = tables;
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
