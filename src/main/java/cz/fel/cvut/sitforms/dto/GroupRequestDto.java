package cz.fel.cvut.sitforms.dto;


import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
@Data
@Accessors(chain = true)
public class GroupRequestDto {
    private String name;
    private Long authorId;
    private List<UserGroupRequestDto> userEntityList;
}
