package ru.fa.io.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record AnimalDto(String id, String name) { }
