package mipt.baranov.database.dto;

import lombok.Data;

import java.time.Month;

@Data
public class CancelledCountByMonth {
    private final Month month;
    private final Number cancelledNum;
}
