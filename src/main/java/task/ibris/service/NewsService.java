package task.ibris.service;

import org.springframework.data.domain.Page;
import task.ibris.dto.NewsDto;
import task.ibris.dto.ResponseDto;

import java.util.List;

public interface NewsService {
    ResponseDto add(NewsDto news);
    ResponseDto<Page<NewsDto>> getAll(Integer page, Integer size);
    ResponseDto<NewsDto> getByName(String name);
    ResponseDto delete(Integer id);
}
