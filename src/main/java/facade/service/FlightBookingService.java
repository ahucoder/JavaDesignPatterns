package facade.service;

import java.time.LocalDate;

public interface FlightBookingService {
    String bookFlight(String passenger, String from, String to, LocalDate date);
}
