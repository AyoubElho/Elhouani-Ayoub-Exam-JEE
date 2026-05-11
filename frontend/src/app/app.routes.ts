import { Routes } from '@angular/router';
import { Vehicules } from './pages/vehicules/vehicules';
import { Agences } from './pages/agences/agences';
import { Locations } from './pages/locations/locations';



export const routes: Routes = [
  {
    path: 'vehicules',
    component: Vehicules,
  },

  {
    path: 'agences',
    component: Agences,
  },

  {
    path: 'locations',
    component: Locations,
  },

  {
    path: '',
    redirectTo: 'vehicules',
    pathMatch: 'full',
  },
];
