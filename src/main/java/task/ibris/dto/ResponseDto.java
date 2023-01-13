package task.ibris.dto;

import lombok.*;
import org.springframework.context.MessageSource;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ResponseDto<T> {
    private Integer code;
    private String message;
    private Boolean success;
    private List errors;
    private T data;
}
