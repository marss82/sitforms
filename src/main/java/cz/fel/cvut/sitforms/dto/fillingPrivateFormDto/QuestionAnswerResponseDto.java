package cz.fel.cvut.sitforms.dto.fillingPrivateFormDto;

import cz.fel.cvut.sitforms.dto.VariantResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class QuestionAnswerResponseDto {
    private Long id;
    private String name;
    private String questionType;
    private List<VariantAnswerResponseDto> variantAnswerResponseDtos;
}
