package task.ibris.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import task.ibris.dto.NewsDto;
import task.ibris.dto.ResponseDto;
import task.ibris.dto.SourceDto;
import task.ibris.service.impl.NewsServiceImpl;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {
    @Autowired
    private NewsServiceImpl service;
    @PostMapping
    @ResponseBody
    public ResponseDto add(@RequestBody NewsDto newsDto, HttpServletRequest req) {
        return service.add(req, newsDto);
    }

    @GetMapping( "/{page}{size}")
    @ResponseBody
    public ResponseDto<Page<NewsDto>> getByPage(@PathVariable Integer page, @PathVariable Integer size, HttpServletRequest req) {
        return service.getAll(req, page, size);
    }

    @GetMapping("/{name}")
    @ResponseBody
    public ResponseDto<NewsDto> getByName(@PathVariable String name, HttpServletRequest req) {
        return service.getByName(req, name);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseDto delete(@PathVariable Integer id, HttpServletRequest req) {
        return service.delete(req, id);
    }
}
