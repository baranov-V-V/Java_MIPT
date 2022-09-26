package mipt.baranov.entities;

import lombok.Data;
import lombok.NonNull;

@Data
public class BoardingPass {
    private final @NonNull String ticketNo;
    private final int flightId;
    private final int boardingNo;
    private final @NonNull String seatNo;
}
