package mipt.baranov.database.dto;

import lombok.Data;

import java.time.Month;

@Data
public class CancelledNumFlightsByMonth {
    private final Month month;
    private final Number cancelledNum;
}
