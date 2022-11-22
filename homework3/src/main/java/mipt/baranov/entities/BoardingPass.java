package mipt.baranov.entities;

import lombok.Data;
import lombok.NonNull;

@Data
public class BoardingPass {
    private final @NonNull String ticketNo;
    private final int flightId;
    private final int boardingNo;
    private final @NonNull String seatNo;

    public BoardingPass(@NonNull String ticketNo, int flightId, int boardingNo, @NonNull String seatNo) {
        this.ticketNo = ticketNo;
        this.flightId = flightId;
        this.boardingNo = boardingNo;
        this.seatNo = seatNo;
    }
}
