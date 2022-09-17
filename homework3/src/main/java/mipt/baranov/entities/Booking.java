package mipt.baranov.entities;

import lombok.Data;
import lombok.NonNull;

import java.time.ZonedDateTime;

@Data
public class Booking {
    private final String bool_ref;
    private @NonNull ZonedDateTime book_date;
    private double total_amount;
}
