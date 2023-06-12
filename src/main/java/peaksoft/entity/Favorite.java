package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "favorites")
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "favorite_gen")
    @SequenceGenerator(name = "favorite_gen",sequenceName = "favorite_seq",allocationSize = 1)
    private Long id;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private User user;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private Product product;
}
