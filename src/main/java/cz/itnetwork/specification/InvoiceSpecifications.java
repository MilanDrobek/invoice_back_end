package cz.itnetwork.specification;

import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.domain.Specification;

public class InvoiceSpecifications {

    public static Specification<InvoiceEntity> hasBuyer(Long buyerId) {
        return (root, query, criteriaBuilder) -> {
            if (buyerId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("buyer").get("id"), buyerId);
        };
    }

    public static Specification<InvoiceEntity> hasSeller(Long sellerId) {
        return (root, query, criteriaBuilder) -> {
            if (sellerId == null) {
                return null;
            }
            return criteriaBuilder.equal(root.get("seller").get("id"), sellerId);
        };
    }

    public static Specification<InvoiceEntity> containsProduct(String product) {
        return (root, query, criteriaBuilder) -> {
            if (product == null || product.isEmpty()) {
                return null;
            }
            return criteriaBuilder.like(root.get("product"), "%" + product + "%");
        };
    }

    public static Specification<InvoiceEntity> hasMinPrice(Long minPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null) {
                return null; /
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
        };
    }

    public static Specification<InvoiceEntity> hasMaxPrice(Long maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (maxPrice == null) {
                return null;
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }
}
