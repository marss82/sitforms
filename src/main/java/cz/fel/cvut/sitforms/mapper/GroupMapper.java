package cz.fel.cvut.sitforms.mapper;

import cz.fel.cvut.sitforms.dto.*;
import cz.fel.cvut.sitforms.model.FormEntity;
import cz.fel.cvut.sitforms.model.GroupEntity;
import cz.fel.cvut.sitforms.model.QuestionEntity;
import org.mapstruct.Mapper;

@Mapper(uses = UserMapper.class)
public interface GroupMapper {
    GroupEntity toEntity(GroupRequestDto groupRequestDto);
    GroupResponseDto toResponse(GroupEntity groupEntity);
}
