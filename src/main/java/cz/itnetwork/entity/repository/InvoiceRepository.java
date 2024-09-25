package cz.itnetwork.entity.repository;

import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {

    @Query("SELECT i FROM invoice i JOIN i.seller s WHERE s.identificationNumber = :identificationNumber")
    List<InvoiceEntity> findAllBySellerIN(@Param("identificationNumber") String identificationNumber);

    @Query("SELECT i FROM invoice i JOIN i.buyer s WHERE s.identificationNumber = :identificationNumber")
    List<InvoiceEntity> findAllByBuyerIN(@Param("identificationNumber") String identificationNumber);

    @Query("SELECT new cz.itnetwork.dto.InvoiceStatisticsDTO(" +
            "COALESCE(SUM(CASE WHEN YEAR(i.issued) = YEAR(CURRENT_DATE) THEN i.price ELSE 0 END), 0), " +
            "COALESCE(SUM(i.price), 0), " +
            "COALESCE(COUNT(i), 0)) " +
            "FROM invoice i")
    InvoiceStatisticsDTO findStatistics();

}

