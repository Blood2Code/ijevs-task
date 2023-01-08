package task.ibris.service;

import task.ibris.dto.NewsDto;
import task.ibris.dto.SourceDto;
import task.ibris.dto.ThematicDto;
import task.ibris.dto.ValidatorDto;

import java.util.List;

public interface ValidatorService {
    List<ValidatorDto> validateSource(SourceDto source);
    List<ValidatorDto> validateThematic(ThematicDto thematic);
    List<ValidatorDto> validateNews(NewsDto news);
}
