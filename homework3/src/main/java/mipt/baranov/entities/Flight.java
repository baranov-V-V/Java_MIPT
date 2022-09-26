package mipt.baranov.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
public class Flight {
    private int flightId;
    private final @NonNull String flightNo;
    private final @NonNull ZonedDateTime scheduledDeparture;
    private final @NonNull ZonedDateTime scheduledArrival;
    private final @NonNull String departureAirport;
    private final @NonNull String arrivalAirport;
    private final @NonNull String status;
    private final @NonNull String aircraftCode;
    private final ZonedDateTime actualDeparture;
    private final ZonedDateTime actualArrival;
}
