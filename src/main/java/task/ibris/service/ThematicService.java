package task.ibris.service;

import org.springframework.data.domain.Page;
import task.ibris.dto.ResponseDto;
import task.ibris.dto.ThematicDto;

public interface ThematicService {
    ResponseDto add(ThematicDto thematic);

    ResponseDto<Page<ThematicDto>> getAll(Integer page, Integer size);

    ResponseDto<ThematicDto> getByName(String name);

    ResponseDto delete(Integer id);
}
