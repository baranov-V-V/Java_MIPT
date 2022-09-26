package mipt.baranov.entities;

import lombok.Data;
import lombok.NonNull;

@Data
public class Seat {
    private final @NonNull String aircraftCode;
    private final @NonNull String seatNo;
    private final @NonNull String fareCondition;
}
