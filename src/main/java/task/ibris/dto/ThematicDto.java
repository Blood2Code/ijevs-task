package task.ibris.dto;

import lombok.*;
import task.ibris.entity.Source;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ThematicDto {
    private Integer id;
    private String name;
    private List<NewsDto> news;
    private Integer source_id;
}
