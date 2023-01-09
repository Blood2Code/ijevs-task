package task.ibris.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import task.ibris.dto.ResponseDto;
import task.ibris.dto.SourceDto;
import task.ibris.dto.ThematicDto;
import task.ibris.dto.ValidatorDto;
import task.ibris.repository.ThematicRepository;
import task.ibris.service.ThematicService;
import task.ibris.service.ValidatorService;
import task.ibris.service.mapper.SourceMapper;
import task.ibris.service.mapper.ThematicMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

@Service
@RequiredArgsConstructor
@Slf4j
public class ThematicServiceImpl implements ThematicService {
    private final ThematicRepository repository;
    private final ResourceBundle bundle;
    private final ValidatorService validator;

    @Override
    public ResponseDto add(ThematicDto thematic) {
        try {
            List<ValidatorDto> errors = validator.validateThematic(thematic);
            if (!errors.isEmpty()) {
                return ResponseDto.builder()
                        .code(-3)
                        .errors(errors)
                        .message(bundle.getString("response.empty_field"))
                        .build();
            }
            repository.save(ThematicMapper.toEntity(thematic));
            return ResponseDto.builder()
                    .code(0)
                    .message(bundle.getString("response.added"))
                    .build();
        }catch (Exception e) {
            Marker marker = MarkerFactory.getMarker("fatal");
            log.error(marker,  "Error while adding new source to database : {}", e.getMessage());
            return ResponseDto.<String>builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<Page<ThematicDto>> getAll(Integer page, Integer size) {
        ResponseDto<Page<ThematicDto>> response;
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<ThematicDto> list = repository.findAll(pageable).map(ThematicMapper::toDto);
            response = ResponseDto.<Page<ThematicDto>>builder()
                    .code(0)
                    .message(bundle.getString("response.success"))
                    .success(true)
                    .data(list)
                    .build();
        } catch (Exception e) {
            log.error("Error while getting all product info by page and size :: {}", e.getMessage());
            response = ResponseDto.<Page<ThematicDto>>builder()
                    .code(-1)
                    .message(bundle.getString("response.error"))
                    .build();
        }
        return response;
    }

    @Override
    public ResponseDto<ThematicDto> getByName(String name) {
        try {
            if (!name.isEmpty()) {
                return ResponseDto.<ThematicDto>builder()
                        .code(-3)
                        .errors(Collections.singletonList(new ArrayList<>().add("Error")))
                        .message(bundle.getString("response.valid_error") + "\n" + bundle.getString("response.empty_field"))
                        .build();
            }
            return ResponseDto.<ThematicDto>builder()
                    .code(0)
                    .data(ThematicMapper.toDto(repository.findByName(name)))
                    .message("Thematic with name" + name + " found!")
                    .build();
        } catch (Exception e) {
            Marker marker = MarkerFactory.getMarker("fatal");
            log.error(marker,  "Error while getting Thematics by name from database : {}", e.getMessage());
            return ResponseDto.<ThematicDto>builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto delete(Integer id) {
        try {
            if (id >= 0) {
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
            return ResponseDto.<SourceDto>builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
        }
    }
}
