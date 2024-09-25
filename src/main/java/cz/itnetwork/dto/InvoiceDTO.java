package cz.itnetwork.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDTO {

    private int invoiceNumber;

    private PersonDTO seller;

    private PersonDTO buyer;

    @JsonFormat (pattern = "yyyy-MM-dd")
    private LocalDate issued;

    @JsonFormat (pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    private String product;

    private long price;

    private int vat;

    private String note;

    @JsonProperty("_id")
    private long id;

}

