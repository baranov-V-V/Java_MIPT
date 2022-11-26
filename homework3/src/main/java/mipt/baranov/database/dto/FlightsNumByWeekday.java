package mipt.baranov.database.dto;

import lombok.Data;
import lombok.NonNull;

import java.time.DayOfWeek;

@Data
public class FlightsNumByWeekday {
    private final DayOfWeek day;
    private Integer arrivalNum;
    private Integer departureNum;
}
