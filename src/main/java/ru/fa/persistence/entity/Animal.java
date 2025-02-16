package ru.fa.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "herd")
public class Animal {
    @Id
    private UUID id;
    @ManyToOne(fetch = FetchType.EAGER)
    private AnimalCategory animalCategory;
    @ManyToOne(fetch = FetchType.EAGER)
    private Corral corral;
    private String name;
}
