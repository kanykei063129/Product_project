package peaksoft.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import peaksoft.enums.Category;

import java.time.ZonedDateTime;
import java.util.List;

@Builder
public record ProductResponse(Long id,
                              String name,
                              int price,
                              List<String> images,
                              String characteristic,
                              String madeIn,
                              Category category,
                              Boolean isFavorite) {

    public ProductResponse(Long id, String name, int price, List<String> images, String characteristic, String madeIn, Category category, Boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.images = images;
        this.characteristic = characteristic;
        this.madeIn = madeIn;
        this.category = category;
        this.isFavorite = isFavorite;
}
}
