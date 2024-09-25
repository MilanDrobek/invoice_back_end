package cz.itnetwork.controller;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public InvoiceDTO addInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.addInvoice(invoiceDTO);
    }

    @GetMapping
    public List<InvoiceDTO> getInvoices(
            @RequestParam(required = false) Long buyerID,      // Filtr podle ID kupujícího
            @RequestParam(required = false) Long sellerID,     // Filtr podle ID prodávajícího
            @RequestParam(required = false) String product,    // Filtr podle názvu produktu
            @RequestParam(required = false) Long minPrice,     // Filtr podle minimální ceny
            @RequestParam(required = false) Long maxPrice,     // Filtr podle maximální ceny
            @RequestParam(required = false) Integer limit      // Volitelný limit (bez výchozí hodnoty)
    ) {
        int effectiveLimit = (limit != null && limit > 0) ? limit : Integer.MAX_VALUE;

        return invoiceService.getAll(buyerID, sellerID, product, minPrice, maxPrice, effectiveLimit);
    }

    @GetMapping("/{id}")
    public InvoiceDTO getInvoiceById(@PathVariable long id) {
        return invoiceService.getInvoiceById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInvoice(@PathVariable long id) {
        invoiceService.removeInvoice(id);
    }

    @PutMapping("/{id}")
    public InvoiceDTO updateInvoice(@PathVariable long id, @RequestBody InvoiceDTO invoiceDTO) {
        return invoiceService.updateInvoice(id, invoiceDTO);
    }

    @GetMapping("/statistics")
    public InvoiceStatisticsDTO getStatistics() {
        return invoiceService.getStatistics();
    }
}
