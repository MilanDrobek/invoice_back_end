package cz.itnetwork.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class InvoiceStatisticsDTO {
    private long currentYearSum;
    private long allTimeSum;
    private long invoicesCount;
}
