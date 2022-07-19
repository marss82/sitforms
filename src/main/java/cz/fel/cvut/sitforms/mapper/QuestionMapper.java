package cz.fel.cvut.sitforms.mapper;

import cz.fel.cvut.sitforms.dto.QuestionRequestDto;
import cz.fel.cvut.sitforms.dto.QuestionResponseDto;
import cz.fel.cvut.sitforms.model.QuestionEntity;
import org.mapstruct.Mapper;

@Mapper(uses = VariantMapper.class)
public interface QuestionMapper {

    QuestionEntity toEntity(QuestionRequestDto questionRequestDto);

    QuestionResponseDto toResponse(QuestionEntity questionEntity);

}
