package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;

import java.util.List;

public interface InvoiceService {

    InvoiceDTO addInvoice(InvoiceDTO invoiceDTO);
    List<InvoiceDTO> getAll(Long buyerID, Long sellerID, String product, Long minPrice, Long maxPrice, int limit) ;
    List<InvoiceDTO> getAllBySellerIN(String identificationNumber);
    List<InvoiceDTO> getAllByBuyerIN(String identificationNumber);
    InvoiceDTO getInvoiceById(long id);
    void removeInvoice(long id);
    InvoiceDTO updateInvoice(long id, InvoiceDTO invoiceDTO);
    InvoiceStatisticsDTO getStatistics();
}

