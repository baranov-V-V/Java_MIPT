package mipt.baranov.database.dto;

import lombok.Data;
import lombok.NonNull;

import java.time.Duration;

@Data
public class SmallestFligthByCity {
    private final @NonNull String cityName;
    private final @NonNull String destName;
    private final Duration flightDuration;
}
