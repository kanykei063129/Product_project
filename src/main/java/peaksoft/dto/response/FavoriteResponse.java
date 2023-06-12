package peaksoft.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.Product;
import peaksoft.entity.User;


@Builder
public record FavoriteResponse(Long id,
                               User userId,
                               Product product) {
    public FavoriteResponse(Long id, User userId, Product product) {
        this.id = id;
        this.userId = userId;
        this.product = product;
    }
}
