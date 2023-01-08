package task.ibris.dto;

import lombok.*;

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
