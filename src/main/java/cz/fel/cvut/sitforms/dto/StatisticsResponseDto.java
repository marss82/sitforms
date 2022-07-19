package cz.fel.cvut.sitforms.dto;

import lombok.Data;

import java.util.List;

@Data
public class StatisticsResponseDto {
    private Long id;
    private String formName;
    private String description;
    private int viewsCount;
    private List<QuestionResponseDto> questionEntities;
}
