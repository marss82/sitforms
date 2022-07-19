package cz.fel.cvut.sitforms.service;

import cz.fel.cvut.sitforms.dto.TokenResponseDto;
import cz.fel.cvut.sitforms.dto.UserRequestDto;
import cz.fel.cvut.sitforms.dto.UserResponseDto;
import cz.fel.cvut.sitforms.model.UserEntity;

import java.util.List;

public interface UserService {

    List<UserResponseDto> findUsers();

    UserResponseDto findUserById(Long id);

    TokenResponseDto createUser(UserRequestDto userRequestDto);

    UserRequestDto updateUser(UserRequestDto userRequestDto, Long userId);

    void initImport();

    UserResponseDto findByEmail(String email);

}
