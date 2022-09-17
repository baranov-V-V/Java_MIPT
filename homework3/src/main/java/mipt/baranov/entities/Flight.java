package mipt.baranov.entities;

import lombok.Data;
import lombok.NonNull;

import java.time.ZonedDateTime;

@Data
public class Flight {
    private int flight_id;
    private final @NonNull String flight_no;
    private final @NonNull ZonedDateTime scheduled_departure;
    private final @NonNull ZonedDateTime scheduled_arrival;
    private final @NonNull String departure_airport;
    private final @NonNull String arrival_airport;
    private final @NonNull String status;
    private final @NonNull String aircraft_code;
    private final ZonedDateTime actual_departure;
    private final ZonedDateTime actual_arrival;
}
