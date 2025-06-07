package facade;

import facade.dto.TripDetailsDto;
import facade.facade.TravelFacade;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {
        TripDetailsDto trip = new TripDetailsDto.Builder()
                .customerName("Alice")
                .origin("Beijing")
                .destination("Shanghai")
                .departureDate(LocalDate.of(2025, 6, 7))
                .returnDate(LocalDate.of(2025, 6, 8))
                .build();

        TravelFacade facade = new TravelFacade.Builder()
                .trip(trip)
                .build();

        facade.bookAll();
    }
}
