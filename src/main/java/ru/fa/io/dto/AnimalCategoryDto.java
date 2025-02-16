package ru.fa.io.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record AnimalCategoryDto(String id, String name) { }
