package com.example.restaurantbookingsystem.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateSlot {
    private LocalDate date;
    private List<TimeSlot> timeSlots;

    public DateSlot(LocalDate date, List<TimeSlot> timeSlots) {
        this.date = date;
        this.timeSlots = timeSlots;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<TimeSlot> getTimeSlots() {
        if(timeSlots == null)
            return new ArrayList<>();
        return timeSlots;
    }

    public void setTimeSlots(List<TimeSlot> timeSlots) {
        this.timeSlots = timeSlots;
    }
}
