package task.ibris.service;

import jakarta.servlet.http.HttpServletRequest;
import task.ibris.dto.NewsDto;
import task.ibris.dto.SourceDto;
import task.ibris.dto.ThematicDto;
import task.ibris.dto.ValidatorDto;

import java.util.List;
import java.util.ResourceBundle;

public interface ValidatorService {
    List<ValidatorDto> validateSource(SourceDto source, ResourceBundle res);
    List<ValidatorDto> validateThematic(ThematicDto thematic, ResourceBundle res);
    List<ValidatorDto> validateNews(NewsDto news, ResourceBundle res);
}
