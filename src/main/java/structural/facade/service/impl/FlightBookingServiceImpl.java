package structural.facade.service.impl;

import structural.facade.service.FlightBookingService;

import java.time.LocalDate;
import java.util.UUID;

public class FlightBookingServiceImpl implements FlightBookingService {
    @Override
    public String bookFlight(String passenger, String from, String to, LocalDate date) {
        String flightInfo = STR."FLIGHT-\{UUID.randomUUID()}";
        System.out.printf("[%s] booking %s->%s flight for %s, date: %s%n", flightInfo, from, to, passenger, date);
        return flightInfo;
    }
}
