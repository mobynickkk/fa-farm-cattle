package ru.fa.service.mapper;

import org.mapstruct.Mapper;
import ru.fa.io.dto.HerdDto;
import ru.fa.persistence.entity.Herd;

@Mapper(componentModel = "spring", uses = {AnimalMapper.class, CorralMapper.class})
public interface HerdMapper extends EntityDtoMapper<Herd, HerdDto> { }
