package cz.fel.cvut.sitforms.dto.fillingPrivateFormDto;

import lombok.Data;

@Data
public class VariantAnswerResponseDto {
    private Long id;
    private String name;
    private boolean answeredVariant;
}
