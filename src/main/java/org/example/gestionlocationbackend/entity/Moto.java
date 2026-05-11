package org.example.gestionlocationbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.gestionlocationbackend.enumeartion.TypeMoto;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DiscriminatorValue("MOTO")
public class Moto extends Vehicule {

    private double cylindree;

    @Enumerated(EnumType.STRING)
    private TypeMoto typeMoto;

    private boolean casqueInclus;
}