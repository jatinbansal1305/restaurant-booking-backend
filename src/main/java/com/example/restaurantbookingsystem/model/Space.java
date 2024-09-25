package com.example.restaurantbookingsystem.model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public abstract class Space {
    protected String id;
    protected int capacity;

    public Space(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
