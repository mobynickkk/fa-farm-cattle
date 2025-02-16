package ru.fa.io.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.OffsetDateTime;

@Builder(toBuilder = true)
public record AnimalDto(String id,
                        CorralDto corral,
                        AnimalCategoryDto animalCategory,
                        String name,
                        @JsonFormat(pattern = "dd.MM.yyyy hh:mm")
                        OffsetDateTime birthDate) { }
