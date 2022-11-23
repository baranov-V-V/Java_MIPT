package mipt.baranov.database.dto;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class MultiAirportCity {
    private final @NonNull String cityName;
    private final List<String> airportCodes;
}
