package task.ibris.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity(name = "ibris_source")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @OneToMany(mappedBy = "id", fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    private List<Thematic> thematics;
}
