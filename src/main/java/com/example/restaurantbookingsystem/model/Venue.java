package com.example.restaurantbookingsystem.model;

import java.time.LocalDate;

public abstract class Venue {
    private String id;
    private String name;
    private Address address;

    public Venue(String id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Address getAddress() { return address; }
}
