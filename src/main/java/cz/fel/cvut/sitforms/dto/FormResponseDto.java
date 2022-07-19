package cz.fel.cvut.sitforms.dto;

import cz.fel.cvut.sitforms.model.FormType;
import cz.fel.cvut.sitforms.model.QuestionEntity;
import cz.fel.cvut.sitforms.model.UserEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class FormResponseDto {
    private Long id;
    private String formName;
    private String description;
    private int viewsCount;
    private boolean instantStatistics;
    private String formType;
    private List<UserResponseDto> formUsers;
    private UserResponseDto user;
    private List<QuestionResponseDto> questionEntities;
}
