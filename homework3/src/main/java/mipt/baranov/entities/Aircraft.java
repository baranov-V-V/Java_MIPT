package mipt.baranov.entities;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class Aircraft {
    private final String aircraft_code;
    private final JsonNode model;
    private final int range;
}
