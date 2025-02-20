package ru.fa.service.mapper;

import org.mapstruct.Mapper;
import ru.fa.io.dto.AnimalDto;
import ru.fa.persistence.entity.Animal;

@Mapper(componentModel = "spring", uses = {AnimalMapper.class, CorralMapper.class})
public interface HerdMapper extends EntityDtoMapper<Animal, AnimalDto> { }
