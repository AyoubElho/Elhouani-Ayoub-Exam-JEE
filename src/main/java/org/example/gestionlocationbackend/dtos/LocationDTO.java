package org.example.gestionlocationbackend.dtos;

import lombok.Data;

import java.util.Date;

@Data
public class LocationDTO {

    private Long id;

    private Date dateDebut;

    private Date dateFin;

    private double montant;

    private Long vehiculeId;
}