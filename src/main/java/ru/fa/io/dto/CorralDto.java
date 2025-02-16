package ru.fa.io.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.OffsetDateTime;

@Builder(toBuilder = true)
public record CorralDto(String id,
                        String name,
                        String place,
                        @JsonFormat(pattern = "dd.MM.yyyy hh:mm")
                        OffsetDateTime acquisitionDate,
                        int capacity) { }
