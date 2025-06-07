package facade.service;

import java.time.LocalDate;

public interface TaxiBookingService {
    String bookCar(String passenger, String city, LocalDate from, LocalDate to);
}
