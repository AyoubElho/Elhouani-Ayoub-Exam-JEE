package org.example.gestionlocationbackend.repository;

import org.example.gestionlocationbackend.entity.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehiculeRepository
        extends JpaRepository<Vehicule, Long> {
}