package com.example.restaurantbookingsystem.model;

import com.example.restaurantbookingsystem.model.enums.FoodType;

import java.util.List;

public class Cuisine {
    private String id;
    private String name;
    private List<String> ingredients;
    private double price;
    private double rating;
    private FoodType type;

    public Cuisine(String id, String name, List<String> ingredients, double price, double rating, FoodType type) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.rating = rating;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public FoodType getType() {
        return type;
    }

    public void setType(FoodType type) {
        this.type = type;
    }
}
