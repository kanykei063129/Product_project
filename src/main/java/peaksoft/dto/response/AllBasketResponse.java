package peaksoft.dto.response;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AllBasketResponse(
        String userEmail,
        int productAmount,
        BigDecimal price
) {
    public AllBasketResponse(String userEmail, int productAmount, BigDecimal price) {
        this.userEmail = userEmail;
        this.productAmount = productAmount;
        this.price = price;
    }
}
