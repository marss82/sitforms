package cz.fel.cvut.sitforms.mapper;

import cz.fel.cvut.sitforms.dto.UserRequestDto;
import cz.fel.cvut.sitforms.dto.UserResponseDto;
import cz.fel.cvut.sitforms.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserEntity toEntity(UserRequestDto userRequestDto);

    UserResponseDto toResponse(UserEntity userEntity);
}
