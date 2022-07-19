package cz.fel.cvut.sitforms.dto;

import cz.fel.cvut.sitforms.model.QuestionType;
import lombok.Data;

import java.util.List;

@Data
public class QuestionRequestDto {
    private String name;
    private QuestionType questionType;
    private List<VariantRequestDto> variantEntityList;

}
