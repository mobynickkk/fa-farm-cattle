package ru.fa.io.dto;

import lombok.Builder;

@Builder(toBuilder = true)
public record CorralDto(String id, String name) { }
