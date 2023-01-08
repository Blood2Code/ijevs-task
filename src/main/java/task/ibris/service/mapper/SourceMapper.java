package task.ibris.service.mapper;

import task.ibris.dto.SourceDto;
import task.ibris.entity.Source;

public class SourceMapper {
    public static SourceDto toDto(Source source) {
        return SourceDto.builder()
                .id(source.getId())
                .name(source.getName())
                .thematics(source.getThematics().stream().map(ThematicMapper::toDto).toList())
                .build();
    }

    public static Source toEntity(SourceDto source) {
        return Source.builder()
                .id(source.getId())
                .name(source.getName())
                .thematics(source.getThematics().stream().map(ThematicMapper::toEntity).toList())
                .build();
    }
}
