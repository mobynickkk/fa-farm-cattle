package ru.fa.io.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record AnimalDto(String id,
                        CorralDto corral,
                        AnimalCategoryDto animal,
                        String name) { }
