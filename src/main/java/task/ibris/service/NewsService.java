package task.ibris.service;

import task.ibris.dto.NewsDto;
import task.ibris.dto.ResponseDto;

import java.util.List;

public interface NewsService {
    ResponseDto add(NewsDto news);
    List<NewsDto> getAll();
    ResponseDto<NewsDto> getByName(String name);
}
