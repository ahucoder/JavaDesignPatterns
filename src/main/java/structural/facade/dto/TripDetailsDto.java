package structural.facade.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class TripDetailsDto {
    private final String passengerName;
    private final LocalDate departureDate;
    private final LocalDate returnDate;
    private final String origin;
    private final String destination;

    private TripDetailsDto(Builder builder) {
        this.passengerName = builder.customerName;
        this.departureDate = builder.departureDate;
        this.returnDate = builder.returnDate;
        this.origin = builder.origin;
        this.destination = builder.destination;
    }

    public static class Builder {
        private String customerName;
        private LocalDate departureDate;
        private LocalDate returnDate;
        private String origin;
        private String destination;

        public Builder customerName(String name) {
            this.customerName = name;
            return this;
        }
        public Builder departureDate(LocalDate date) {
            this.departureDate = date;
            return this;
        }
        public Builder returnDate(LocalDate date) {
            this.returnDate = date;
            return this;
        }
        public Builder origin(String origin) {
            this.origin = origin;
            return this;
        }
        public Builder destination(String dest) {
            this.destination = dest;
            return this;
        }
        public TripDetailsDto build() {
            if (departureDate.isAfter(returnDate)) {
                throw new IllegalArgumentException("Departure must be before return date");
            }
            return new TripDetailsDto(this);
        }
    }
}
