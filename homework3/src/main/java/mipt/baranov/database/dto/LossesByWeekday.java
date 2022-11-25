package mipt.baranov.database.dto;
import lombok.Data;
import lombok.NonNull;

import java.time.DayOfWeek;

@Data
public class LossesByWeekday {
    private final @NonNull String city;
    private final DayOfWeek day;
    private final Double lossAmount;
}
