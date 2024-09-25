package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 19.0.2 (Eclipse Adoptium)"
)
@Component
public class InvoiceMapperImpl implements InvoiceMapper {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public InvoiceDTO toDTO(InvoiceEntity invoiceEntity) {
        if ( invoiceEntity == null ) {
            return null;
        }

        InvoiceDTO invoiceDTO = new InvoiceDTO();

        invoiceDTO.setInvoiceNumber( invoiceEntity.getInvoiceNumber() );
        invoiceDTO.setSeller( personMapper.toDTO( invoiceEntity.getSeller() ) );
        invoiceDTO.setBuyer( personMapper.toDTO( invoiceEntity.getBuyer() ) );
        invoiceDTO.setIssued( invoiceEntity.getIssued() );
        invoiceDTO.setDueDate( invoiceEntity.getDueDate() );
        invoiceDTO.setProduct( invoiceEntity.getProduct() );
        invoiceDTO.setPrice( invoiceEntity.getPrice() );
        invoiceDTO.setVat( invoiceEntity.getVat() );
        invoiceDTO.setNote( invoiceEntity.getNote() );
        invoiceDTO.setId( invoiceEntity.getId() );

        return invoiceDTO;
    }

    @Override
    public InvoiceEntity toEntity(InvoiceDTO invoiceDTO) {
        if ( invoiceDTO == null ) {
            return null;
        }

        InvoiceEntity invoiceEntity = new InvoiceEntity();

        invoiceEntity.setInvoiceNumber( invoiceDTO.getInvoiceNumber() );
        invoiceEntity.setSeller( personMapper.toEntity( invoiceDTO.getSeller() ) );
        invoiceEntity.setBuyer( personMapper.toEntity( invoiceDTO.getBuyer() ) );
        invoiceEntity.setIssued( invoiceDTO.getIssued() );
        invoiceEntity.setDueDate( invoiceDTO.getDueDate() );
        invoiceEntity.setProduct( invoiceDTO.getProduct() );
        invoiceEntity.setPrice( invoiceDTO.getPrice() );
        invoiceEntity.setVat( invoiceDTO.getVat() );
        invoiceEntity.setNote( invoiceDTO.getNote() );
        invoiceEntity.setId( invoiceDTO.getId() );

        return invoiceEntity;
    }
}
