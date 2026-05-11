package org.example.gestionlocationbackend.repository;

import org.example.gestionlocationbackend.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository
        extends JpaRepository<Location, Long> {
}