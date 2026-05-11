package org.example.gestionlocationbackend.repository;



import org.example.gestionlocationbackend.entity.AppUser;
import org.springframework.data.jpa.repository
        .JpaRepository;

import java.util.Optional;

public interface AppUserRepository
        extends JpaRepository<AppUser,Long> {

    Optional<AppUser>
    findByUsername(String username);
}