package cz.fel.cvut.sitforms.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionsResponseDto {
    private Long id;
    private String formName;
    private String description;
    private List<QuestionResponseDto> questionEntities;
}
