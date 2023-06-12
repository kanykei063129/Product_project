package peaksoft.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.entity.Product;
import peaksoft.entity.User;

@Builder
public record FavoriteRequest(User user,
                              Product product) {
}
