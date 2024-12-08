package ru.fa.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fa.persistence.entity.Corral;

import java.util.UUID;

@Repository
public interface CorralRepository extends JpaRepository<Corral, UUID> {
}
