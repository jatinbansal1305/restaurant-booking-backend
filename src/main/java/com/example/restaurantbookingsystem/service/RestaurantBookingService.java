package com.example.restaurantbookingsystem.service;

import com.example.restaurantbookingsystem.model.DateSlot;
import com.example.restaurantbookingsystem.model.Restaurant;
import com.example.restaurantbookingsystem.model.RestaurantBooking;
import com.example.restaurantbookingsystem.model.TimeSlot;
import com.example.restaurantbookingsystem.repository.RestaurantBookingRepository;
import com.example.restaurantbookingsystem.repository.RestaurantRepository;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RestaurantBookingService implements BookingService<RestaurantBooking>{

    private final RestaurantBookingRepository restaurantBookingRepository ;
    private final RestaurantRepository restaurantRepository ;


    public RestaurantBookingService(RestaurantBookingRepository restaurantBookingRepository, RestaurantRepository restaurantRepository) {
        this.restaurantBookingRepository = restaurantBookingRepository;
        this.restaurantRepository = restaurantRepository;
    }
    @Override
    public synchronized void makeBooking(RestaurantBooking restaurantBooking) throws Exception {
        List<TimeSlot> availableSlots = getAvailableSlotsForDate(
                restaurantBooking.getEntityId(),
                restaurantBooking.getBookingDate()
        );

        ZonedDateTime requestedZonedDateTime = convertToUtc(restaurantBooking.getBookingTime(), restaurantBooking.getBookingDate());

        System.out.println("Requested Time: " + restaurantBooking.getBookingTime());

        processBooking(availableSlots, requestedZonedDateTime, restaurantBooking);
    }

    private ZonedDateTime convertToUtc(String bookingTime, LocalDate bookingDate) {
        ZoneId localZone = ZoneId.of("Asia/Kolkata"); // IST
        LocalTime localTime = LocalTime.parse(bookingTime, DateTimeFormatter.ofPattern("HH:mm:ss"));

        return localTime.atDate(bookingDate)
                .atZone(localZone)
                .withZoneSameInstant(ZoneId.of("UTC"));
    }

    private void processBooking(List<TimeSlot> availableSlots, ZonedDateTime requestedZonedDateTime, RestaurantBooking restaurantBooking) throws Exception {
        Optional<TimeSlot> matchingSlot = findMatchingSlot(availableSlots, requestedZonedDateTime);

        if (matchingSlot.isPresent() && !matchingSlot.get().isBooked()) {
            System.out.println("Matching Slot Found: " + matchingSlot.get().getTime());

            TimeSlot availableTimeSlot = matchingSlot.get();
            updateSlotAvailability(availableTimeSlot);

            saveBooking(restaurantBooking, availableSlots);
        } else {
            System.out.println("No Matching Slot Found for: " + restaurantBooking.getBookingTime());
            throw new Exception("No table is available for the booking");
        }
    }

    private Optional<TimeSlot> findMatchingSlot(List<TimeSlot> availableSlots, ZonedDateTime requestedZonedDateTime) {
        return availableSlots.stream()
                .filter(slot -> {
                    ZonedDateTime slotZonedDateTime = ZonedDateTime.parse(slot.getTime())
                            .withZoneSameInstant(ZoneId.of("UTC"));
                    return requestedZonedDateTime.isEqual(slotZonedDateTime) && !slot.isBooked();
                })
                .findFirst();
    }

    private void updateSlotAvailability(TimeSlot availableTimeSlot) {
        availableTimeSlot.setNumberOfTables(availableTimeSlot.getNumberOfTables() - 1);


        if (availableTimeSlot.getNumberOfTables() == 0) {
            availableTimeSlot.bookSlot();
        }
    }

    private void saveBooking(RestaurantBooking restaurantBooking, List<TimeSlot> availableSlots) {
        restaurantBookingRepository.makeBooking(restaurantBooking);
        restaurantRepository.updateRestaurant(restaurantBooking.getEntityId(), availableSlots);
    }


    @Override
    public void cancelBooking(String bookingId) {

    }

    @Override
    public RestaurantBooking getBookingDetails(String bookingId) {
        return null;
    }

    private List<TimeSlot> getAvailableSlotsForDate(String restaurantId, LocalDate date) {

        Restaurant restaurant = restaurantRepository.findById(restaurantId);

        List<DateSlot> dateSlots = restaurant.getDateSlots();


        List<TimeSlot> availableSlots = dateSlots.stream()
                .filter(dateSlot -> dateSlot.getDate().equals(date))
                .flatMap(dateSlot -> dateSlot.getTimeSlots().stream())
                .filter(slot->!slot.isBooked())
                .collect(Collectors.toList());


        return availableSlots;
    }
}
