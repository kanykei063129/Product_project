package peaksoft.dto.request;

import lombok.*;
import peaksoft.entity.Product;
import peaksoft.entity.User;

import java.util.List;

@Builder
public record BasketRequest(List<Product>productsId,
                            User userId){
}
