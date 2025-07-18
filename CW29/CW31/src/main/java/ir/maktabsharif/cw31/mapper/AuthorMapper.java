package ir.maktabsharif.cw31.mapper;

import ir.maktabsharif.cw31.dto.author.AuthorFindResponse;
import ir.maktabsharif.cw31.dto.author.AuthorSaveUpdateRequest;
import ir.maktabsharif.cw31.model.Author;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthorMapper {
    Author mapToEntity(AuthorSaveUpdateRequest authorSaveUpdateRequest);

    AuthorFindResponse mapToResponse(Author author);
}
