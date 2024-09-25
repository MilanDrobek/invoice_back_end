package cz.itnetwork.dto;
import lombok.*;

@Data
@AllArgsConstructor
public class PersonStatisticsDTO {
    private Long personId;
    private String personName;
    private long revenue;
}
