package mipt.baranov.entities;

import lombok.Data;
import lombok.NonNull;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.ZonedDateTime;

@Data
public class Flight {
    private int flight_id;
    private final @NonNull String flight_no;
    private final @NonNull Timestamp scheduled_departure;
    private final @NonNull Timestamp scheduled_arrival;
    private final @NonNull String departure_airport;
    private final @NonNull String arrival_airport;
    private final @NonNull String status;
    private final @NonNull String aircraft_code;
    private final Timestamp actual_departure;
    private final Timestamp actual_arrival;
}
