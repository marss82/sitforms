package cz.fel.cvut.sitforms.dto;

import lombok.Data;

@Data
public class VariantResponseDto {
    private Long id;
    private String name;
    private int clickNumbers;
}
