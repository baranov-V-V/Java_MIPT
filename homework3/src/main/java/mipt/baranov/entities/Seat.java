package mipt.baranov.entities;

import lombok.Data;
import lombok.NonNull;

@Data
public class Seat {
    private final @NonNull String aircraft_code;
    private final @NonNull String seat_no;
    private final @NonNull String fare_condition;
}
