package mipt.baranov.database.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class CancelledFlightCities {
    private final @NonNull String cityName;
    private final Integer cancelledNum;
}
