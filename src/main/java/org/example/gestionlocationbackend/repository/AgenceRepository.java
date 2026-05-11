package org.example.gestionlocationbackend.repository;

import org.example.gestionlocationbackend.entity.Agence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgenceRepository
        extends JpaRepository<Agence, Long> {
}