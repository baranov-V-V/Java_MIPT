package mipt.baranov.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Data
public class Flight {
    public Flight(int flightId, @NonNull String flightNo, @NonNull ZonedDateTime scheduledDeparture, @NonNull ZonedDateTime scheduledArrival, @NonNull String departureAirport, @NonNull String arrivalAirport, @NonNull FlightStatus status, @NonNull String aircraftCode, ZonedDateTime actualDeparture, ZonedDateTime actualArrival) {
        this.flightId = flightId;
        this.flightNo = flightNo;
        this.scheduledDeparture = scheduledDeparture;
        this.scheduledArrival = scheduledArrival;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.status = status;
        this.aircraftCode = aircraftCode;
        this.actualDeparture = actualDeparture;
        this.actualArrival = actualArrival;
    }

    public enum FlightStatus {
        Scheduled("Scheduled"),
        OnTime   ("OnTime"),
        Delayed  ("Delayed"),
        Departed ("Departed"),
        Arrived  ("Arrived"),
        Cancelled("Cancelled");

        private final String title;

        FlightStatus(String title) {
            this.title = title;
        }

        public String getStringStatus() {
            return title;
        }
    }

    private int flightId;
    private final @NonNull String flightNo;
    private final @NonNull ZonedDateTime scheduledDeparture;
    private final @NonNull ZonedDateTime scheduledArrival;
    private final @NonNull String departureAirport;
    private final @NonNull String arrivalAirport;
    private final @NonNull FlightStatus status;
    private final @NonNull String aircraftCode;
    private final ZonedDateTime actualDeparture;
    private final ZonedDateTime actualArrival;
}
