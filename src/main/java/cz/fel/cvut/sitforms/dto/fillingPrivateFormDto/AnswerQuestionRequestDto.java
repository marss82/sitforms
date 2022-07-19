package cz.fel.cvut.sitforms.dto.fillingPrivateFormDto;

import lombok.Data;

import java.util.List;

@Data
public class AnswerQuestionRequestDto {

    private Long chosenQuestionId;
    private List<AnswerVariantRequestDto> chosenVariants;
}
