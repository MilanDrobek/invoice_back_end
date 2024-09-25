package cz.itnetwork.dto.mapper;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface InvoiceMapper {


    InvoiceDTO toDTO(InvoiceEntity invoiceEntity);

    InvoiceEntity toEntity(InvoiceDTO invoiceDTO);

}

