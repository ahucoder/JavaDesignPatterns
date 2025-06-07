package structural.facade.service.impl;

import structural.facade.service.HotelBookingService;

import java.time.LocalDate;
import java.util.UUID;

public class HotelBookingServiceImpl implements HotelBookingService {
    @Override
    public String bookHotel(String passenger, String city, LocalDate from, LocalDate to) {
        String hotelInfo = STR."HOTEL-\{UUID.randomUUID()}";
        System.out.printf("[%s] booking hotel in %s for %s, checkin: %s, checkout: %s%n",
                hotelInfo, city, passenger, from, to);
        return hotelInfo;
    }
}
