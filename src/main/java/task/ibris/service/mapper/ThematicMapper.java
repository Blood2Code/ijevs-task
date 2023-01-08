package task.ibris.service.mapper;

import task.ibris.dto.ThematicDto;
import task.ibris.entity.Thematic;

public class ThematicMapper {
    public static ThematicDto toDto(Thematic thematic) {
        return ThematicDto.builder()
                .id(thematic.getId())
                .name(thematic.getName())
                .news(thematic.getNews().stream().map(NewsMapper::toDtoOutOfThematic).toList())
                .build();
    }
    public static Thematic toEntity(ThematicDto thematic) {
        return Thematic.builder()
                .id(thematic.getId())
                .name(thematic.getName())
                .news(thematic.getNews().stream().map(NewsMapper::toEntityOutOfThematic).toList())
                .build();
    }
}
