package task.ibris.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import task.ibris.dto.NewsDto;
import task.ibris.dto.SourceDto;
import task.ibris.dto.ThematicDto;
import task.ibris.dto.ValidatorDto;
import task.ibris.service.ValidatorService;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    @Override
    public List<ValidatorDto> validateSource(SourceDto source, ResourceBundle res) {
        List<ValidatorDto> errors = new ArrayList<>();
        if (!StringUtils.hasText(source.getName())) {
            errors.add(new ValidatorDto("name", res.getString("response.empty_field")));
        }
        return errors;
    }

    @Override
    public List<ValidatorDto> validateThematic(ThematicDto thematic, ResourceBundle res) {
        List<ValidatorDto> errors = new ArrayList<>();
        if (!StringUtils.hasText(thematic.getName())) {
            errors.add(new ValidatorDto("name", res.getString("response.empty_field")));
        }
        return errors;
    }

    @Override
    public List<ValidatorDto> validateNews(NewsDto news, ResourceBundle res) {
        List<ValidatorDto> errors = new ArrayList<>();
        if (!StringUtils.hasText(news.getName())) {
            errors.add(new ValidatorDto("name", res.getString("response.empty_field")));
        }
        return errors;
    }
}
