package mipt.baranov.entities;

import lombok.Data;
import lombok.NonNull;
import org.json.JSONObject;

@Data
public class Aircraft {
    private final @NonNull String aircraftCode;
    private final @NonNull JSONObject model;
    private final @NonNull int range;
}
