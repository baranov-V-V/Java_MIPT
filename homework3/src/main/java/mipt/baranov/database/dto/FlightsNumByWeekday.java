package mipt.baranov.database.dto;

import lombok.Data;
import lombok.NonNull;

import java.time.DayOfWeek;

@Data
public class FlightsNumByWeekday {
    private final @NonNull String cityName;
    private final DayOfWeek day;
    private final Integer arrivalNum;
    private final Integer departureNum;
}
