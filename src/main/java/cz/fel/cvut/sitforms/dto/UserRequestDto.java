package cz.fel.cvut.sitforms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserRequestDto {

    private String email;
    private String username;
    private String password;
    private String name;
    private String surname;

}
