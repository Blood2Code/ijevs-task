package task.ibris.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import task.ibris.dto.ResponseDto;
import task.ibris.dto.SourceDto;
import task.ibris.dto.ValidatorDto;
import task.ibris.repository.SourceRepository;
import task.ibris.service.SourceService;
import task.ibris.service.ValidatorService;
import task.ibris.service.mapper.SourceMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

@Service
@RequiredArgsConstructor
@Slf4j
public class SourceServiceImpl implements SourceService {
    private final ResourceBundle bundle;
    private final SourceRepository repository;
    private final ValidatorService validator;

    @Override
    public ResponseDto add(SourceDto source) {
        try {
            List<ValidatorDto> errors = validator.validateSource(source);
            if (!errors.isEmpty()) {
                return ResponseDto.builder()
                        .code(-3)
                        .errors(errors)
                        .message(bundle.getString("response.valid_error"))
                        .data(bundle.getString("response.failed"))
                        .build();
            }
            repository.save(SourceMapper.toEntity(source));
            return ResponseDto.<String>builder().success(true).message(bundle.getString("response.success")).build();
        }catch (Exception e) {
            Marker marker = MarkerFactory.getMarker("fatal");
            log.error(marker,  "Error while adding new source to database : {}", e.getMessage());
            return ResponseDto.<String>builder().success(false).message(e.getMessage()).build();
        }

    }

    @Override
    public List<SourceDto> getAll() {
        return repository.findAll().stream().map(SourceMapper::toDto).toList();
    }

    @Override
    public ResponseDto<SourceDto> getByName(String name) {
        try {
            if (!name.isEmpty()) {
                return ResponseDto.<SourceDto>builder()
                        .code(-3)
                        .errors(Collections.singletonList(new ArrayList<>().add("Error")))
                        .message(bundle.getString("response.valid_error") + "\n" + bundle.getString("response.empty_field"))
                        .build();
            }
            return ResponseDto.<SourceDto>builder()
                    .code(0)
                    .data(SourceMapper.toDto(repository.findByName(name)))
                    .message("Source with name" + name + " found!")
                    .build();
        }catch (Exception e) {
            Marker marker = MarkerFactory.getMarker("fatal");
            log.error(marker,  "Error while getting source from database : {}", e.getMessage());
            return ResponseDto.<SourceDto>builder().success(false).message(e.getMessage()).build();
        }
    }

    @Override
    public ResponseDto deleteById(Integer id) {
        try {
            if (id <= 0) {
                return ResponseDto.builder()
                        .code(-2)
                        .message(bundle.getString("response.not_found"))
                        .errors(Collections.singletonList(new ArrayList<>().add(bundle.getString("response.failed"))))
                        .success(false)
                        .build();
            }
            repository.deleteById(id);
            return ResponseDto.builder()
                    .code(0)
                    .success(true)
                    .message(bundle.getString("response.deleted"))
                    .build();
        }catch (Exception e) {
            Marker marker = MarkerFactory.getMarker("fatal");
            log.error(marker,  "Error while deleting source from database : {}", e.getMessage());
            return ResponseDto.<SourceDto>builder().success(false).message(e.getMessage()).build();
        }
    }
}
