package mipt.baranov.entities;

import lombok.Data;
import lombok.NonNull;

import java.sql.Timestamp;

@Data
public class Booking {
    private final String bool_ref;
    private @NonNull Timestamp book_date;
    private double total_amount;
}
