package mipt.baranov.entities;

import lombok.Data;
import lombok.NonNull;

import java.time.ZonedDateTime;

@Data
public class Booking {
    private final String boolRef;
    private final @NonNull ZonedDateTime bookDate;
    private final double totalAmount;
}
