package mipt.baranov.entities;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.NonNull;
import mipt.baranov.util.Point;

@Data
public class Airport {
    private final @NonNull String airport_code;
    private final @NonNull JsonNode airport_name;
    private final @NonNull JsonNode city;
    private final @NonNull Point coordinates;
    private final @NonNull String timezone;
}
