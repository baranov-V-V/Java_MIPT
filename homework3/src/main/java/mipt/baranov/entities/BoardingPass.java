package mipt.baranov.entities;

import lombok.Data;
import lombok.NonNull;

@Data
public class BoardingPass {
    private final @NonNull String ticket_no;
    private final int flight_id;
    private final int boarding_no;
    private final @NonNull String seat_no;
}
