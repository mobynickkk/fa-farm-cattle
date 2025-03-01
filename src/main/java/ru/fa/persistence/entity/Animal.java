package ru.fa.persistence.entity;

import jakarta.persistence.*;
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
@Table(name = "herd")
public class Animal {
    @Id
    private UUID id;
    @ManyToOne(fetch = FetchType.EAGER)
    private AnimalCategory animalCategory;
    @ManyToOne(fetch = FetchType.EAGER)
    private Corral corral;
    private String username;
    private String name;
    private OffsetDateTime birthDate;
}
