package cz.fel.cvut.sitforms.dto;

import cz.fel.cvut.sitforms.model.FormType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class FormRequestDto {
    private String formName;
    private String description;
    private Long authorId;
    private List<FormUserRequestDto> assignedUsers;
    private FormType formType;
    private boolean instantStatistics;
    private List<QuestionRequestDto> questionEntities;
}
