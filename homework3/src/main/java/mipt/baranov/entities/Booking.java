package mipt.baranov.entities;

import lombok.Data;
import lombok.NonNull;

import java.time.ZonedDateTime;

@Data
public class Booking {
    private final String boolRef;
    private final @NonNull ZonedDateTime bookDate;
    private final double totalAmount;

    public Booking(String boolRef, @NonNull ZonedDateTime bookDate, double totalAmount) {
        this.boolRef = boolRef;
        this.bookDate = bookDate;
        this.totalAmount = totalAmount;
    }
}
