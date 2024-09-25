package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.dto.mapper.*;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.*;
import cz.itnetwork.specification.InvoiceSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.webjars.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    @Autowired
    private PersonServiceImpl personServiceImpl;

    @Override
    public InvoiceDTO addInvoice(InvoiceDTO invoiceDTO) {
        InvoiceEntity entity = invoiceMapper.toEntity(invoiceDTO);
        PersonEntity addBuyer = personServiceImpl.validatePerson(entity.getBuyer().getId());
        entity.setBuyer(addBuyer);
        PersonEntity addSeller =  personServiceImpl.validatePerson(entity.getSeller().getId());
        entity.setSeller(addSeller);
        invoiceRepository.save(entity);
        return invoiceMapper.toDTO(validateInvoice(entity.getId()));
    }

    @Override
    public List<InvoiceDTO> getAll(Long buyerID, Long sellerID, String product, Long minPrice, Long maxPrice, int limit) {
        // Vytvoření dynamických filtrů pomocí Specification
        Specification<InvoiceEntity> spec = Specification.where(InvoiceSpecifications.hasBuyer(buyerID))
                .and(InvoiceSpecifications.hasSeller(sellerID))
                .and(InvoiceSpecifications.containsProduct(product))
                .and(InvoiceSpecifications.hasMinPrice(minPrice))
                .and(InvoiceSpecifications.hasMaxPrice(maxPrice));

        // PageRequest pro omezení počtu vrácených faktur (limit)
        PageRequest pageRequest = PageRequest.of(0, limit); // Stránkování začíná od indexu 0

        // Pokud nejsou parametry zadány, Specification se vrátí jako `null`, což vrátí všechny faktury
        return invoiceRepository.findAll(spec, pageRequest) // findAll s limitováním počtu výsledků
                .stream()
                .map(invoiceMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDTO> getAllBySellerIN(String identificationNumber) {
        return invoiceRepository.findAllBySellerIN(identificationNumber)
                .stream()
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvoiceDTO> getAllByBuyerIN(String identificationNumber) {
        return invoiceRepository.findAllByBuyerIN(identificationNumber)
                .stream()
                .map(i -> invoiceMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    @Override
    public InvoiceDTO getInvoiceById(long id) {
        return invoiceMapper.toDTO(validateInvoice(id));
    }

    @Override
    public InvoiceDTO updateInvoice(long id, InvoiceDTO invoiceDTO) {
        validateInvoice(id);
        invoiceDTO.setId(id);
        return addInvoice(invoiceDTO);
    }

    @Override
    public void removeInvoice(long id) {
        validateInvoice(id);
        invoiceRepository.deleteById(id);
    }

    @Override
    public InvoiceStatisticsDTO getStatistics() {
        return invoiceRepository.findStatistics();
    }

    private InvoiceEntity validateInvoice(long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Invoice with id " + id + " wasn't found in the database."));
    }

}
