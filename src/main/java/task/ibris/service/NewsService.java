package task.ibris.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import task.ibris.dto.NewsDto;
import task.ibris.dto.ResponseDto;

import java.net.http.HttpRequest;
import java.util.List;

public interface NewsService {
    ResponseDto add(HttpServletRequest req, NewsDto news);


    ResponseDto<Page<NewsDto>> getAll(HttpServletRequest req, Integer page, Integer size);

    ResponseDto<NewsDto> getByName(HttpServletRequest req, String name);
    ResponseDto delete(HttpServletRequest req,Integer id);
}
