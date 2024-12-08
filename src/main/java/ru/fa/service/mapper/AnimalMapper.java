package ru.fa.service.mapper;

import org.mapstruct.Mapper;
import ru.fa.io.dto.AnimalDto;
import ru.fa.persistence.entity.Animal;

@Mapper(componentModel = "spring")
public interface AnimalMapper extends EntityDtoMapper<Animal, AnimalDto> { }
