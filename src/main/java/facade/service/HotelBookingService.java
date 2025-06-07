package facade.service;

import java.time.LocalDate;

public interface HotelBookingService {
    String bookHotel(String passenger, String city, LocalDate from, LocalDate to);
}
