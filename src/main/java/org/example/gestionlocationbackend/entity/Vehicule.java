package org.example.gestionlocationbackend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.gestionlocationbackend.entity.Agence;
import org.example.gestionlocationbackend.enumeartion.StatutVehicule;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "TYPE_VEHICULE",
        discriminatorType = DiscriminatorType.STRING
)
public abstract  class Vehicule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marque;

    private String modele;

    private String matricule;

    private double prixParJour;

    private Date dateMiseEnService;

    @Enumerated(EnumType.STRING)
    private StatutVehicule statut;

    @ManyToOne
    private Agence agence;

    @OneToMany(mappedBy = "vehicule")
    private List<Location> locations;
}