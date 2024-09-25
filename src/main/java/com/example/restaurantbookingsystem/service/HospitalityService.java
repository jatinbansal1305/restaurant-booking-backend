package com.example.restaurantbookingsystem.service;

import com.example.restaurantbookingsystem.model.DateSlot;

import java.util.List;
import java.util.Map;

public interface HospitalityService<T> {
    void register(T entity);
    void addSlots(String entityId, List<DateSlot> slots);
    List<T> search(Map<String, String> criteria);
}