package ru.fa.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "corral")
public class Corral {
    @Id
    private UUID id;
    private String name;
    private String place;
    private OffsetDateTime acquisitionDate;
    private int capacity;
}
