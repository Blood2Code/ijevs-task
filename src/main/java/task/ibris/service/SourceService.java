package task.ibris.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import task.ibris.dto.ResponseDto;
import task.ibris.dto.SourceDto;

public interface SourceService {
    ResponseDto add(SourceDto source, HttpServletRequest req);
    ResponseDto<Page<SourceDto>> getAll(Integer page, Integer size, HttpServletRequest req);
    ResponseDto<SourceDto> getByName(String name, HttpServletRequest req);
    ResponseDto deleteById(Integer id, HttpServletRequest req);
}
