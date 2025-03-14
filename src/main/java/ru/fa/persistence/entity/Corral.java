package ru.fa.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "corral")
public class Corral {
    @Id
    private UUID id;
    private String username;
    private String name;
    private String place;
    private OffsetDateTime acquisitionDate;
    private int capacity;
}
