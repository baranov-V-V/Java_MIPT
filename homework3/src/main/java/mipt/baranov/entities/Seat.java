package mipt.baranov.entities;

import lombok.Data;
import lombok.NonNull;

@Data
public class Seat {
    public enum SeatType {
        Economy("Economy"),
        Comfort("Comfort"),
        Business("Business");

        private final String title;

        SeatType(String title) {
            this.title = title;
        }

        public String getStringType() {
            return title;
        }
    }

    private final @NonNull String aircraftCode;
    private final @NonNull String seatNo;
    private final @NonNull SeatType fareCondition;
}
