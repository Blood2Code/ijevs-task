package task.ibris.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class NewsDto {
    private Integer id;
    private String name;
    private ThematicDto thematic;
}
