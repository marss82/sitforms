package cz.fel.cvut.sitforms.mapper;

import cz.fel.cvut.sitforms.dto.VariantRequestDto;
import cz.fel.cvut.sitforms.dto.VariantResponseDto;
import cz.fel.cvut.sitforms.model.VariantEntity;
import org.mapstruct.Mapper;

@Mapper
public interface VariantMapper {

    VariantEntity toEntity(VariantRequestDto variantRequestDto);

    VariantResponseDto toResponse(VariantEntity variantEntity);

}
