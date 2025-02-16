package ru.fa.service.mapper;

import org.mapstruct.Mapper;
import ru.fa.io.dto.AnimalCategoryDto;
import ru.fa.persistence.entity.AnimalCategory;

@Mapper(componentModel = "spring")
public interface AnimalMapper extends EntityDtoMapper<AnimalCategory, AnimalCategoryDto> { }
