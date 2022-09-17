package mipt.baranov.entities;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;
import lombok.NonNull;

@Data
public class Ticket {
    private final @NonNull String ticket_no;
    private final @NonNull String book_ref;
    private final @NonNull String passenger_id;
    private final @NonNull String passenger_name;
    private final JsonNode contact_data;
}
