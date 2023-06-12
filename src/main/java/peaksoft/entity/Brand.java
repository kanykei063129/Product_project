package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "brands")
@Setter @Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "brand_gen")
    @SequenceGenerator(name = "brand_gen",sequenceName = "brand_seq",allocationSize = 1)
    private Long id;
    private String brandName;
    private String image;
    @OneToMany(mappedBy = "brand",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private List<Product>products;
}
