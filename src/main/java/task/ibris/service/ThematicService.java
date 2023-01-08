package task.ibris.service;

import task.ibris.dto.ResponseDto;
import task.ibris.dto.ThematicDto;

import java.util.List;

public interface ThematicService {
    ResponseDto add(ThematicDto thematic);
    List<ThematicDto> getAll();
    ResponseDto<ThematicDto> getByName(String name);
}
