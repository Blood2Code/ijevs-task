package task.ibris.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import task.ibris.dto.*;
import task.ibris.repository.NewsRepository;
import task.ibris.service.NewsService;
import task.ibris.service.ValidatorService;
import task.ibris.service.mapper.NewsMapper;
import task.ibris.service.mapper.ThematicMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final ValidatorService validator;
    private final NewsRepository repository;
    private final ResourceBundle bundle;

    @Override
    public ResponseDto add(NewsDto news) {
        try {
            List<ValidatorDto> errors = validator.validateNews(news);
            if (!errors.isEmpty()) {
                return ResponseDto.builder()
                        .code(-3)
                        .errors(errors)
                        .message(bundle.getString("response.empty_field"))
                        .build();
            }
            repository.save(NewsMapper.toEntity(news));
            return ResponseDto.builder()
                    .code(0)
                    .message(bundle.getString("response.added"))
                    .build();
        }catch (Exception e) {
            Marker marker = MarkerFactory.getMarker("fatal");
            log.error(marker,  "Error while adding new news to database : {}", e.getMessage());
            return ResponseDto.<String>builder()
                    .success(false)
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<Page<NewsDto>> getAll(Integer page, Integer size) {
        ResponseDto<Page<NewsDto>> response;
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<NewsDto> list = repository.findAll(pageable).map(NewsMapper::toDto);
            response = ResponseDto.<Page<NewsDto>>builder()
                    .code(0)
                    .success(true)
                    .data(list)
                    .build();
        } catch (Exception e) {
            log.error("Error while getting all product info by page and size :: {}", e.getMessage());
            response = ResponseDto.<Page<NewsDto>>builder()
                    .code(-1)
                    .message(bundle.getString("response.error"))
                    .build();
        }
        return response;
    }

    @Override
    public ResponseDto<NewsDto> getByName(String name) {
        try {
            if (!name.isEmpty()) {
                return ResponseDto.<NewsDto>builder()
                        .code(-3)
                        .errors(Collections.singletonList(new ArrayList<>().add("Error")))
                        .message(bundle.getString("response.valid_error") + "\n" + bundle.getString("response.empty_field"))
                        .build();
            }
            return ResponseDto.<NewsDto>builder()
                    .code(0)
                    .data(NewsMapper.toDto(repository.findByName(name)))
                    .message("Thematic with name" + name + " found!")
                    .build();
        } catch (Exception e) {
            Marker marker = MarkerFactory.getMarker("fatal");
            log.error(marker,  "Error while adding new news to database : {}", e.getMessage());
            return ResponseDto.<NewsDto>builder()
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
