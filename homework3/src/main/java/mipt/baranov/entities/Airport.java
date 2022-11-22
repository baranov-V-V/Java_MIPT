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

    public Airport(@NonNull String airportCode, @NonNull String airportName, @NonNull String city, @NonNull Point coordinates, @NonNull String timezone) {
        this.airportCode = airportCode;
        this.airportName = new JSONObject(airportName);
        this.city = new JSONObject(city);
        this.coordinates = coordinates;
        this.timezone = timezone;
    }
}
