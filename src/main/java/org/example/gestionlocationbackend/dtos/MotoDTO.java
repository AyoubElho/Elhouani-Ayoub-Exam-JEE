package org.example.gestionlocationbackend.dtos;

import lombok.Data;

@Data
public class MotoDTO extends VehiculeDTO {

    private double cylindree;

    private String typeMoto;

    private boolean casqueInclus;
}