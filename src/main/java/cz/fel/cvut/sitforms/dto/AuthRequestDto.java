package cz.fel.cvut.sitforms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class AuthRequestDto {

    private String username;
    private String password;
}