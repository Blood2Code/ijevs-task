package task.ibris.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "ibris_news")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    private Thematic thematic;
}
