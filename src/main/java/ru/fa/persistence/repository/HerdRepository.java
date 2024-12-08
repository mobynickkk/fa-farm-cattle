package ru.fa.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fa.persistence.entity.Herd;

import java.util.UUID;

@Repository
public interface HerdRepository extends JpaRepository<Herd, UUID> { }