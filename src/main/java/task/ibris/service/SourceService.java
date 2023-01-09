package task.ibris.service;

import org.springframework.data.domain.Page;
import task.ibris.dto.ResponseDto;
import task.ibris.dto.SourceDto;

public interface SourceService {
    ResponseDto add(SourceDto source);
    ResponseDto<Page<SourceDto>> getAll(Integer page, Integer size);
    ResponseDto<SourceDto> getByName(String name);
    ResponseDto deleteById(Integer id);
}
