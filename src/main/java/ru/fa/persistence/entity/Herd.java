package ru.fa.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@Entity
@Table(name = "herd")
public class Herd {
    @Id
    private UUID id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Animal animal;
    @ManyToOne(fetch = FetchType.EAGER)
    private Corral corral;
    private Integer amount;
}
