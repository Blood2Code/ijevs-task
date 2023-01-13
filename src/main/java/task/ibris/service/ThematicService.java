package task.ibris.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import task.ibris.dto.ResponseDto;
import task.ibris.dto.ThematicDto;

public interface ThematicService {
    ResponseDto add(ThematicDto thematic, HttpServletRequest req);

    ResponseDto<Page<ThematicDto>> getAll(Integer page, Integer size, HttpServletRequest req);

    ResponseDto<ThematicDto> getByName(String name, HttpServletRequest req);

    ResponseDto delete(Integer id, HttpServletRequest req);
}
