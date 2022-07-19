package cz.fel.cvut.sitforms.dto.fillingPrivateFormDto;

import cz.fel.cvut.sitforms.model.FormType;
import lombok.Data;

import java.util.List;

@Data
public class SendFormDto {

    private Long formId;
    private Long userId;
    private List<AnswerQuestionRequestDto> questionAnswers;

}
