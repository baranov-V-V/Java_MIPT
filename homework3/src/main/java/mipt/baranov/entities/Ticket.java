package mipt.baranov.entities;

import lombok.Data;
import lombok.NonNull;
import org.json.JSONObject;

@Data
public class Ticket {
    private final @NonNull String ticket_no;
    private final @NonNull String book_ref;
    private final @NonNull String passenger_id;
    private final @NonNull String passenger_name;
    private final JSONObject contact_data;
}
