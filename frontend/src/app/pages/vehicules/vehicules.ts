import { Component, OnInit } from '@angular/core';

import { Vehicule } from '../../entity/Vehicule';

import { VehiculeService } from '../../service/VehiculeService';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../service/AuthService';

@Component({
  selector: 'app-vehicules',
  imports: [CommonModule, RouterLink],
  templateUrl: './vehicules.html',
  styleUrl: './vehicules.css',
})
export class Vehicules implements OnInit {
  vehicules: Vehicule[] = [];
  trackById(index: number, item: any) {
    return item.id;
  }
  constructor(
    private vehiculeService: VehiculeService,
    private authService: AuthService,
  ) {}

  ngOnInit(): void {
    this.getVehicules();
  }

  getVehicules() {
    this.vehiculeService.getVehicules().subscribe({
      next: (data) => {
        this.vehicules = data;
        console.log(data);
      },

      error: (err) => {
        console.log(err);
      },
    });
  }

  deleteVehicule(id: any) {
    this.vehiculeService.deleteVehicule(id).subscribe({
      next: () => {
        this.getVehicules();
      },

      error: (err) => {
        console.log(err);
      },
    });
  }

  canCreateVehicule(): boolean {
    return this.authService.hasAnyRole([
      'ROLE_EMPLOYE',
      'ROLE_ADMIN',
    ]);
  }

  canDeleteVehicule(): boolean {
    return this.authService.hasRole('ROLE_ADMIN');
  }
}
