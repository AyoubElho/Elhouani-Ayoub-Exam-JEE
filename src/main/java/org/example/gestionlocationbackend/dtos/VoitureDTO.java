package org.example.gestionlocationbackend.dtos;

import lombok.Data;

@Data
public class VoitureDTO extends VehiculeDTO {

    private int nombrePortes;

    private String typeCarburant;

    private String boiteVitesse;
}