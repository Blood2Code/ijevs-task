package task.ibris.service.mapper;

import task.ibris.dto.ThematicDto;
import task.ibris.entity.Thematic;

public class ThematicMapper {
    public static ThematicDto toDto(Thematic thematic) {
        return ThematicDto.builder()
                .id(thematic.getId())
                .name(thematic.getName())
                .news(thematic.getNews().stream().map(NewsMapper::toDto).toList())
                .source_id(thematic.getSourceId())
                .build();
    }
    public static Thematic toEntity(ThematicDto thematic) {
        return Thematic.builder()
                .id(thematic.getId())
                .name(thematic.getName())
                .sourceId(thematic.getSource_id())
                .news(thematic.getNews().stream().map(NewsMapper::toEntity).toList())
                .build();
    }
    public static Thematic toEntityOutOfNews(ThematicDto thematic) {
        return Thematic.builder()
                .id(thematic.getId())
                .name(thematic.getName())
                .sourceId(thematic.getSource_id())
                .build();
    }

    public static ThematicDto toDtoOutOfNews(Thematic thematic) {
        return ThematicDto.builder()
                .id(thematic.getId())
                .name(thematic.getName())
                .source_id(thematic.getSourceId())
                .build();
    }
}
