package cz.fel.cvut.sitforms.dto.fillingPublicFormDto;

import cz.fel.cvut.sitforms.dto.fillingPrivateFormDto.AnswerQuestionRequestDto;
import lombok.Data;

import java.util.List;

@Data
public class PublicFormDto {
    private Long formId;
    private List<AnswerQuestionRequestDto> answers;
}
