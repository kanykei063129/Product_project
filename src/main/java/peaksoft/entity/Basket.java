package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "baskets")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "basket_gen")
    @SequenceGenerator(name = "basket_gen",sequenceName = "basket_seq",allocationSize = 1)
    private Long id;
    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    private List<Product> products;
    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.PERSIST})
    private User user;
    }
