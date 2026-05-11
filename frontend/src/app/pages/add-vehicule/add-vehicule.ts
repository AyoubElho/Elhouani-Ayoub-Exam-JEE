import { Component } from '@angular/core';

import { CommonModule } from '@angular/common';

import { FormBuilder, FormGroup, ReactiveFormsModule } from '@angular/forms';

import { VehiculeService } from '../../service/VehiculeService';

@Component({
  selector: 'app-add-vehicule',

  standalone: true,

  imports: [CommonModule, ReactiveFormsModule],

  templateUrl: './add-vehicule.html',

  styleUrl: './add-vehicule.css',
})
export class AddVehicule {
  form!: FormGroup;

  constructor(
    private fb: FormBuilder,

    private vehiculeService: VehiculeService,
  ) {
    this.form = this.fb.group({
      typeVehicule: ['VOITURE'],

      marque: [''],

      modele: [''],

      matricule: [''],

      prixParJour: [0],

      statut: ['DISPONIBLE'],

      // VOITURE
      nombrePortes: [4],

      typeCarburant: ['DIESEL'],

      boiteVitesse: ['AUTOMATIQUE'],

      // MOTO
      cylindree: [0],

      typeMoto: ['SPORTIVE'],

      casqueInclus: [true],
    });
  }

  saveVehicule() {
    const data = this.form.value;

    // SAVE VOITURE
    if (data.typeVehicule === 'VOITURE') {
      this.vehiculeService.addVoiture(data).subscribe({
        next: (response) => {
          console.log(response);

          alert('Voiture Added');
        },

        error: (err) => {
          console.log(err);
        },
      });
    }

    // SAVE MOTO
    else if (data.typeVehicule === 'MOTO') {
      this.vehiculeService.addMoto(data).subscribe({
        next: (response) => {
          console.log(response);

          alert('Moto Added');
        },

        error: (err) => {
          console.log(err);
        },
      });
    }
  }
}
