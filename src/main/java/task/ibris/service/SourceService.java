package task.ibris.service;

import task.ibris.dto.ResponseDto;
import task.ibris.dto.SourceDto;

import java.util.List;

public interface SourceService {
    ResponseDto add(SourceDto source);
    List<SourceDto> getAll();
    ResponseDto<SourceDto> getByName(String name);
    ResponseDto deleteById(Integer id);
}
