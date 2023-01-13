package task.ibris.service.impl;

import jakarta.servlet.http.HttpServletRequest;
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
    public ResponseDto add(SourceDto source, HttpServletRequest req) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(bundle.getBaseBundleName(), req.getLocale());
        try {
//            List<ValidatorDto> errors = validator.validateSource(source, resourceBundle);
            if (source.equals(null)) {
                return ResponseDto.builder()
                        .code(-3)
                        .success(false)
                        .message(resourceBundle.getString("response.valid_error"))
                        .data(resourceBundle.getString("response.failed"))
                        .build();
            }
            repository.save(SourceMapper.toEntity(source));
            return ResponseDto.<String>builder()
                    .code(0)
                    .success(true)
                    .message(resourceBundle.getString("response.added"))
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
    public ResponseDto<Page<SourceDto>> getAll(Integer page, Integer size, HttpServletRequest req) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(bundle.getBaseBundleName(), req.getLocale());

        ResponseDto<Page<SourceDto>> response;
        try {
            Pageable pageRequest = PageRequest.of(page, size);
            Page<SourceDto> list = repository.findAll(pageRequest).map(SourceMapper::toDto);

            response = ResponseDto.<Page<SourceDto>>builder()
                    .code(0)
                    .message(resourceBundle.getString("response.success"))
                    .success(true)
                    .data(list)
                    .build();
        }catch (Exception e) {
            log.error("Error while getting all product info by page and size :: {}", e.getMessage());
            response = ResponseDto.<Page<SourceDto>>builder()
                    .code(-1)
                    .message(resourceBundle.getString("response.error"))
                    .build();
        }
        return response;
    }

    @Override
    public ResponseDto<SourceDto> getByName(String name, HttpServletRequest req) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(bundle.getBaseBundleName(), req.getLocale());

        try {
            if (!name.isEmpty()) {
                return ResponseDto.<SourceDto>builder()
                        .code(-3)
                        .errors(Collections.singletonList(new ArrayList<>().add("Error")))
                        .message(resourceBundle.getString("response.valid_error") + "\n" + resourceBundle.getString("response.empty_field"))
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
            return ResponseDto.<SourceDto>builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto deleteById(Integer id, HttpServletRequest req) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(bundle.getBaseBundleName(), req.getLocale());

        try {
            if (id >= 0) {
                return ResponseDto.builder()
                        .code(-2)
                        .message(resourceBundle.getString("response.not_found"))
                        .errors(Collections.singletonList(new ArrayList<>().add(bundle.getString("response.failed"))))
                        .success(false)
                        .build();
            }
            repository.deleteById(id);
            return ResponseDto.builder()
                    .code(0)
                    .success(true)
                    .message(resourceBundle.getString("response.deleted"))
                    .build();
        }catch (Exception e) {
            Marker marker = MarkerFactory.getMarker("fatal");
            log.error(marker,  "Error while deleting source from database : {}", e.getMessage());
            return ResponseDto.<SourceDto>builder().success(false).message(e.getMessage()).build();
        }
    }
}
