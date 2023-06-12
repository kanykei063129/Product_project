package peaksoft.dto.response;

import peaksoft.entity.Comment;
import peaksoft.enums.Category;

import java.util.List;

public record ProductResponseAll(Long id,
                                 String name,
                                 int price,
                                 List<String> images,
                                 String characteristic,
                                 String madeIn,
                                 Category category,
                                 Boolean isFavorite,
                                 List<Comment>comments) {

    public ProductResponseAll(Long id, String name, int price, List<String> images, String characteristic, String madeIn, Category category, Boolean isFavorite, List<Comment> comments) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.images = images;
        this.characteristic = characteristic;
        this.madeIn = madeIn;
        this.category = category;
        this.isFavorite = isFavorite;
        this.comments = comments;
    }
}
