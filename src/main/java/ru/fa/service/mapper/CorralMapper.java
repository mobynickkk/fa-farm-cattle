package ru.fa.service.mapper;

import org.mapstruct.Mapper;
import ru.fa.io.dto.CorralDto;
import ru.fa.persistence.entity.Corral;

@Mapper(componentModel = "spring")
public interface CorralMapper extends EntityDtoMapper<Corral, CorralDto> { }
