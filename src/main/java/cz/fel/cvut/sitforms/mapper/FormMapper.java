package cz.fel.cvut.sitforms.mapper;

import cz.fel.cvut.sitforms.dto.FormRequestDto;
import cz.fel.cvut.sitforms.dto.FormResponseDto;
import cz.fel.cvut.sitforms.dto.QuestionsResponseDto;
import cz.fel.cvut.sitforms.dto.StatisticsResponseDto;
import cz.fel.cvut.sitforms.model.FormEntity;
import org.mapstruct.Mapper;

@Mapper(uses = QuestionMapper.class)
public interface FormMapper {

    FormEntity toEntity(FormRequestDto formRequestDto);

    FormResponseDto toResponse(FormEntity formEntity);

    StatisticsResponseDto toStatisticsResponse(FormEntity formEntity);

    QuestionsResponseDto toQuestions(FormEntity formEntity);


}
