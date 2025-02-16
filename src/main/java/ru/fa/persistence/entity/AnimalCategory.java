package ru.fa.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "animals")
public class AnimalCategory {
    @Id
    private UUID id;
    private String name;
}
