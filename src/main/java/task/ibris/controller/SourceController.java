package task.ibris.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import task.ibris.dto.ResponseDto;
import task.ibris.dto.SourceDto;
import task.ibris.service.SourceService;
import task.ibris.service.impl.SourceServiceImpl;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/source")
public class SourceController {
    @Autowired
    private SourceServiceImpl service;

    @PostMapping
    @ResponseBody
    public ResponseDto add(@RequestBody SourceDto sourceDto, HttpServletRequest req) {
        return service.add(sourceDto ,req);
    }

    @GetMapping( "/{page}/{size}")
    @ResponseBody
    public ResponseDto<Page<SourceDto>> getByPage(@PathVariable Integer page, @PathVariable Integer size, HttpServletRequest req) {
        return service.getAll(page, size, req);
    }

    @GetMapping("/{name}")
    @ResponseBody
    public ResponseDto<SourceDto> getByName(@PathVariable String name, HttpServletRequest req) {
        return service.getByName(name, req);
    }

    @GetMapping("/csv")
    public void getCsv() throws IOException {
        service.exportAsCsv();
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseDto delete(@PathVariable Integer id, HttpServletRequest req) {
        return service.deleteById(id, req);
    }

}
