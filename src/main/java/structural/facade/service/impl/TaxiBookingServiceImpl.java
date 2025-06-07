package structural.facade.service.impl;

import structural.facade.service.TaxiBookingService;

import java.time.LocalDate;
import java.util.UUID;

public class TaxiBookingServiceImpl implements TaxiBookingService {
    @Override
    public String bookCar(String passenger, String city, LocalDate from, LocalDate to) {
        String taxiInfo = STR."CAR-\{UUID.randomUUID()}";
        System.out.printf("[%s] booking taxi for %s in %s, from: %s, to: %s%n", taxiInfo, passenger, city, from, to);
        return taxiInfo;
    }
}
