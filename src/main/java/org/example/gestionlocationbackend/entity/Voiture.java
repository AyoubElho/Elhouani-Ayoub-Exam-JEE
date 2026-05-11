package org.example.gestionlocationbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.gestionlocationbackend.enumeartion.BoiteVitesse;
import org.example.gestionlocationbackend.enumeartion.TypeCarburant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("VOITURE")
public class Voiture extends Vehicule {

    private int nombrePortes;

    @Enumerated(EnumType.STRING)
    private TypeCarburant typeCarburant;

    @Enumerated(EnumType.STRING)
    private BoiteVitesse boiteVitesse;
}