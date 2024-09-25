package com.example.restaurantbookingsystem.controller;

import com.example.restaurantbookingsystem.dto.BookingRequestDTO;
import com.example.restaurantbookingsystem.dto.RestaurantRegistrationDTO;
import com.example.restaurantbookingsystem.mapper.RestaurantBookingMapper;
import com.example.restaurantbookingsystem.mapper.RestaurantMapper;
import com.example.restaurantbookingsystem.model.DateSlot;
import com.example.restaurantbookingsystem.model.Restaurant;
import com.example.restaurantbookingsystem.model.RestaurantBooking;
import com.example.restaurantbookingsystem.model.TimeSlot;
import com.example.restaurantbookingsystem.service.RestaurantBookingService;
import com.example.restaurantbookingsystem.service.RestaurantManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {

    private final RestaurantManager restaurantManager;
    private final RestaurantBookingService restaurantBookingService;

    public RestaurantController(RestaurantManager restaurantManager, RestaurantBookingService restaurantBookingService) {
        this.restaurantManager = restaurantManager;
        this.restaurantBookingService = restaurantBookingService;
    }

    @PostMapping("/register")
    public String registerRestaurant(@RequestBody RestaurantRegistrationDTO restaurantRegistrationDTO) {
        Restaurant restaurant = RestaurantMapper.mapToRestaurant(restaurantRegistrationDTO);
        restaurantManager.register(restaurant);
        return "Restaurant registered successfully!";
    }

    @PostMapping("/{id}/slots")
    public String addSlots(@PathVariable String id, @RequestBody List<DateSlot> dateSlots) {
        restaurantManager.addSlots(id, dateSlots);
        return "Slots added successfully!";
    }

    @GetMapping("/search")
    public List<Restaurant> searchRestaurants(@RequestParam Map<String, String> criteria) {
        return restaurantManager.search(criteria);
    }

    @GetMapping("/{id}/slots")
    public List<LocalTime> getAvailableSlots(@PathVariable String id, @RequestParam String date) {
        LocalDate requestedDate = LocalDate.parse(date);

        System.out.println("id: " + id + " requestedDate: " + requestedDate );

        List<TimeSlot> slots = restaurantManager.getAvailableSlotsForDate(id, requestedDate);

        System.out.println("Slots.size(): " + slots.size() );

        ZoneId istZone = ZoneId.of("Asia/Kolkata");

        if (requestedDate.equals(LocalDate.now())) {
            LocalTime currentTime = LocalTime.now(istZone);

            slots = slots.stream()
                    .filter(slot -> {
                        OffsetDateTime slotDateTime = OffsetDateTime.parse(slot.getTime());
                        LocalTime slotTimeIST = slotDateTime.atZoneSameInstant(istZone).toLocalTime();
                        return slotTimeIST.isAfter(currentTime);
                    })
                    .collect(Collectors.toList());
        }

        return slots.stream()
                .map(slot -> {
                    OffsetDateTime slotDateTime = OffsetDateTime.parse(slot.getTime());
                    return slotDateTime.atZoneSameInstant(istZone).toLocalTime();
                })
                .collect(Collectors.toList());
    }

    @PostMapping("/{id}/book")
    public ResponseEntity<String> bookRestaurant(@PathVariable String id, @RequestBody BookingRequestDTO bookingRequest) throws Exception {
        RestaurantBooking booking = RestaurantBookingMapper.toRestaurantBooking(bookingRequest);

        try {
            restaurantBookingService.makeBooking(booking);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

        return ResponseEntity.ok("Restaurant booked successfully!");
    }

    @GetMapping("/healthcheck")
    public String healthCheck() {
        return "Health check success";
    }

}
