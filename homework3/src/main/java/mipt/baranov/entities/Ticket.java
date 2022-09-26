package mipt.baranov.entities;

import lombok.Data;
import lombok.NonNull;
import org.json.JSONObject;

@Data
public class Ticket {
    private final @NonNull String ticketNo;
    private final @NonNull String bookRef;
    private final @NonNull String passengerId;
    private final @NonNull String passengerName;
    private final JSONObject contactData;
}
