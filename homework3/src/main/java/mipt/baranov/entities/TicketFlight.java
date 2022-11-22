package mipt.baranov.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TicketFlight {
    private final String ticketNo;
    private final int flightId;
    private final String fareConditions;
    private final double amount;
}
