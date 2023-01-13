package task.ibris.service.mapper;


import task.ibris.dto.NewsDto;
import task.ibris.entity.News;

public class NewsMapper {
    public static NewsDto toDto(News news) {
        return NewsDto.builder()
                .id(news.getId())
                .name(news.getName())
                .thematic(ThematicMapper.toDto(news.getThematic()))
                .build();
    }

    public static News toEntity(NewsDto news) {
        return News.builder()
                .id(news.getId())
                .name(news.getName())
                .thematic(ThematicMapper.toEntityOutOfNews(news.getThematic()))
                .build();
    }

    public static NewsDto toDtoOutOfThematic(News news) {
        return NewsDto.builder()
                .id(news.getId())
                .name(news.getName())
                .build();
    }

    public static News toEntityOutOfThematic(NewsDto news) {
        return News.builder()
                .id(news.getId())
                .name(news.getName())
                .build();
    }
}
