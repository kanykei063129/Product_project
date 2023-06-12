package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "comments")
@Setter @Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "comment_gen")
    @SequenceGenerator(name = "comment_gen",sequenceName = "comment_seq",allocationSize = 1)
    private Long id;
    private String comment;
    private ZonedDateTime createdDate;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private User user;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private Product product;
}
