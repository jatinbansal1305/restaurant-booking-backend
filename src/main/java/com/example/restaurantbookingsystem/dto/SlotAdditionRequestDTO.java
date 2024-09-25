package com.example.restaurantbookingsystem.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class SlotAdditionRequestDTO {
    private LocalDate start;
    private LocalDate end;
    private List<LocalTime> slots;

    public SlotAdditionRequestDTO(LocalDate start, LocalDate end, List<LocalTime> slots) {
        this.start = start;
        this.end = end;
        this.slots = slots;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public List<LocalTime> getSlots() {
        return slots;
    }

    public void setSlots(List<LocalTime> slots) {
        this.slots = slots;
    }
}
