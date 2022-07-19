package cz.fel.cvut.sitforms.dto;

import cz.fel.cvut.sitforms.model.UserEntity;
import lombok.Data;

import java.util.List;

@Data
public class GroupResponseDto {
    private Long id;
    private String name;
    private UserResponseDto author;
    private List<UserResponseDto> userEntityList;
}

