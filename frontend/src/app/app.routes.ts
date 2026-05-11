import { Routes } from '@angular/router';
import { Vehicules } from './pages/vehicules/vehicules';
import { Agences } from './pages/agences/agences';
import { Locations } from './pages/locations/locations';
import { AddVehicule } from './pages/add-vehicule/add-vehicule';
import { Login } from './pages/login/login';
import { authGuard } from './security/auth.guard';



export const routes: Routes = [
  {
    path: 'login',
    component: Login,
  },

  {
    path: 'vehicules',
    component: Vehicules,
    canActivate: [authGuard],
    data: {
      roles: [
        'ROLE_CLIENT',
        'ROLE_EMPLOYE',
        'ROLE_ADMIN',
      ],
    },
  },

  {
    path: 'agences',
    component: Agences,
    canActivate: [authGuard],
    data: {
      roles: [
        'ROLE_CLIENT',
        'ROLE_EMPLOYE',
        'ROLE_ADMIN',
      ],
    },
  },

  {
    path: 'locations',
    component: Locations,
    canActivate: [authGuard],
    data: {
      roles: [
        'ROLE_EMPLOYE',
        'ROLE_ADMIN',
      ],
    },
  },

  {
    path: 'add-vehicule',
    component: AddVehicule,
    canActivate: [authGuard],
    data: {
      roles: [
        'ROLE_EMPLOYE',
        'ROLE_ADMIN',
      ],
    },
  },
  {
    path: '',
    redirectTo: 'vehicules',
    pathMatch: 'full',
  },
  {
    path: '**',
    redirectTo: 'vehicules',
  },
];
