package task.ibris.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SourceDto {
    private Integer id;
    private String name;
    private List<ThematicDto> thematics;
}
