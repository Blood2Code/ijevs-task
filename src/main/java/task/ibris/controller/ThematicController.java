package task.ibris.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import task.ibris.dto.ResponseDto;
import task.ibris.dto.ThematicDto;
import task.ibris.service.impl.ThematicServiceImpl;

@RestController
@RequestMapping("/thematic")
@RequiredArgsConstructor
public class ThematicController {
    @Autowired
    private ThematicServiceImpl service;
    @PostMapping
    @ResponseBody
    public ResponseDto add(@RequestBody ThematicDto thematicDto, HttpServletRequest req) {
        return service.add(thematicDto, req);
    }

    @GetMapping( "/{page}/{size}")
    @ResponseBody
    public ResponseDto<Page<ThematicDto>> getByPage(@PathVariable Integer page, @PathVariable Integer size, HttpServletRequest req) {
        return service.getAll(page, size, req);
    }

    @GetMapping("/{name}")
    @ResponseBody
    public ResponseDto<ThematicDto> getByName(@PathVariable String name, HttpServletRequest req) {
        return service.getByName(name, req);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseDto delete(@PathVariable Integer id, HttpServletRequest req) {
        return service.delete(id, req);
    }

}
