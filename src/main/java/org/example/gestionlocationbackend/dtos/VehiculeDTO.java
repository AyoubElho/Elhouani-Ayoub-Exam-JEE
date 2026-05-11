package org.example.gestionlocationbackend.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class VehiculeDTO {

    private Long id;

    private String marque;

    private String modele;

    private String matricule;

    private double prixParJour;

    private Date dateMiseEnService;

    private String statut;

    private String typeVehicule;

    private Long agenceId;
}