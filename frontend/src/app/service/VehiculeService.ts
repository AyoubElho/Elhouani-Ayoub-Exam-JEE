import { Injectable } from '@angular/core';

import { HttpClient } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Vehicule } from '../entity/Vehicule';
import { environment } from '../../environments/environment';


@Injectable({
  providedIn: 'root',
})
export class VehiculeService {
  apiUrl = environment.apiUrl+'vehicules';

  constructor(private http: HttpClient) {}

  getVehicules(): Observable<Vehicule[]> {
    return this.http.get<Vehicule[]>(this.apiUrl);
  }

  deleteVehicule(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  addVoiture(data: any) {
    return this.http.post(`${this.apiUrl}/voitures`, data);
  }

  addMoto(data: any) {
    return this.http.post(`${this.apiUrl}/motos`, data);
  }
}
