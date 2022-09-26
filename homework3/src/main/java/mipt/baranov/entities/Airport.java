package mipt.baranov.entities;

import lombok.Data;
import lombok.NonNull;
import mipt.baranov.util.Point;
import org.json.JSONObject;

@Data
public class Airport {
    private final @NonNull String airportCode;
    private final @NonNull JSONObject airportName;
    private final @NonNull JSONObject city;
    private final @NonNull Point coordinates;
    private final @NonNull String timezone;
}
