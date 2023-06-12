package peaksoft.dto.response;

import lombok.*;
import peaksoft.entity.Product;
import peaksoft.entity.User;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record BasketResponse(
        String name,
        Long productCount,
        BigDecimal totalPrice
) {
    public BasketResponse(String name, Long productCount, BigDecimal totalPrice) {
        this.name = name;
        this.productCount = productCount;
        this.totalPrice = totalPrice;
    }
}
