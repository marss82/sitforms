package cz.fel.cvut.sitforms.dto;

import cz.fel.cvut.sitforms.model.RoleEntity;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class UserResponseDto {
    private Long id;
    private String email;
    private String username;
    private boolean filled;
    private String name;
    private String surname;
}
