package task.ibris.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ValidatorDto {
    private String field;
    private String message;
}
