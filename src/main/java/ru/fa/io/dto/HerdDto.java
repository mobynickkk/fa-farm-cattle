package ru.fa.io.dto;

import lombok.Builder;
import ru.fa.service.mapper.CorralMapper;

import java.time.OffsetDateTime;

@Builder(toBuilder = true)
public record HerdDto(String id,
                      CorralDto corral,
                      AnimalDto animal,
                      Integer amount) { }
