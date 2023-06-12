package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;
import peaksoft.enums.Category;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "product_gen")
    @SequenceGenerator(name = "product_gen",sequenceName = "product_seq",allocationSize = 1)
    private Long id;
    private String name;
    private int price;
    @Lob
    private List<String> images;
    private String characteristic;
    private Boolean isFavorite;
    private String madeIn;
    private Category category;
    @OneToMany(mappedBy = "product",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    private List<Comment>comments;
    @OneToMany(mappedBy = "product",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    private List<Favorite>favorites;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    private Brand brand;
    @ManyToMany(mappedBy = "products",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
    private List<Basket>baskets;
}
