package facade.facade;

import facade.dto.TripDetailsDto;
import facade.service.FlightBookingService;
import facade.service.HotelBookingService;
import facade.service.PaymentService;
import facade.service.TaxiBookingService;
import facade.service.impl.FlightBookingServiceImpl;
import facade.service.impl.HotelBookingServiceImpl;
import facade.service.impl.PaymentServiceImpl;
import facade.service.impl.TaxiBookingServiceImpl;

public class TravelFacade {
    private final FlightBookingService flightService;
    private final HotelBookingService hotelService;
    private final TaxiBookingService taxiService;
    private final PaymentService paymentService;
    private final TripDetailsDto trip;
    private double totalCost;

    private TravelFacade(Builder builder) {
        this.flightService = new FlightBookingServiceImpl();
        this.hotelService = new HotelBookingServiceImpl();
        this.taxiService = new TaxiBookingServiceImpl();
        this.paymentService = new PaymentServiceImpl();
        this.trip = builder.trip;
    }

    public static class Builder {
        private TripDetailsDto trip;

        public Builder trip(TripDetailsDto trip) {
            this.trip = trip;
            return this;
        }

        public TravelFacade build() {
            if (trip == null) {
                throw new IllegalStateException("TripDetails must be provided");
            }
            return new TravelFacade(this);
        }
    }

    public void bookAll() {
        System.out.println("Start the itinerary booking process...");
        // 1. Flight
        String flightConf = flightService.bookFlight(
                trip.getPassengerName(), trip.getOrigin(), trip.getDestination(), trip.getDepartureDate());
        totalCost += 500.0;

        // 2. Hotel
        String hotelConf = hotelService.bookHotel(
                trip.getPassengerName(), trip.getDestination(), trip.getDepartureDate(), trip.getReturnDate());
        totalCost += 300.0;

        // 3. Taxi
        String taxiConf = taxiService.bookCar(
                trip.getPassengerName(), trip.getDestination(), trip.getDepartureDate(), trip.getReturnDate());
        totalCost += 200.0;

        // 4. Pay
        boolean paid = paymentService.processPayment(trip.getPassengerName(), totalCost);
        if (!paid) {
            throw new RuntimeException("Payment failed, booking cancelled");
        }

        System.out.println("Booking completed! The following is the confirmation information:");
        System.out.println("Flight info: " + flightConf);
        System.out.println("Hotel info: " + hotelConf);
        System.out.println("Taxi info: " + taxiConf);
        System.out.printf("Total cost: %.2f%n", totalCost);
    }

}
