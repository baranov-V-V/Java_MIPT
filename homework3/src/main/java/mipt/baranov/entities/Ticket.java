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

    public Ticket(@NonNull String ticketNo, @NonNull String bookRef, @NonNull String passengerId, @NonNull String passengerName, String contactData) {
        this.ticketNo = ticketNo;
        this.bookRef = bookRef;
        this.passengerId = passengerId;
        this.passengerName = passengerName;
        this.contactData = new JSONObject(contactData);
    }
}
