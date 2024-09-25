package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/identification")
public class IdentificationController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/{identificationNumber}/sales")
    public List<InvoiceDTO> getAllBySellerIN(@PathVariable String identificationNumber) {
        return invoiceService.getAllBySellerIN(identificationNumber);
    }

    @GetMapping("/{identificationNumber}/purchases")
    public List<InvoiceDTO> getAllByBuyerIN(@PathVariable String identificationNumber) {
        return invoiceService.getAllByBuyerIN(identificationNumber);
    }
}
