package cz.fel.cvut.sitforms.dto.fillingPrivateFormDto;

import cz.fel.cvut.sitforms.dto.QuestionResponseDto;

import java.util.List;

public class AnswersResponseDto {
    private Long id;
    private String formName;
    private String description;
    private List<QuestionAnswerResponseDto> questionAnswerResponseDtos;
}
