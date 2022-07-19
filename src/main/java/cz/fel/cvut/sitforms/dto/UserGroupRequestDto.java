package cz.fel.cvut.sitforms.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserGroupRequestDto {
    private Long id;
}
