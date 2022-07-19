package cz.fel.cvut.sitforms.dto;

import cz.fel.cvut.sitforms.model.FormEntity;
import cz.fel.cvut.sitforms.model.QuestionType;
import cz.fel.cvut.sitforms.model.VariantEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class QuestionResponseDto {
    private Long id;
    private String name;
    private int totalClickNumbers;
    private String questionType;
    private List<VariantResponseDto> variantEntityList;
}
