import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService, UserRole } from '../service/AuthService';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);

  if (!authService.isAuthenticated()) {
    return router.createUrlTree(['/login'], {
      queryParams: {
        returnUrl: state.url,
      },
    });
  }

  const allowedRoles =
    (route.data['roles'] as UserRole[] | undefined) ?? [];

  if (
    allowedRoles.length > 0
    && !authService.hasAnyRole(allowedRoles)
  ) {
    return router.createUrlTree(['/vehicules']);
  }

  return true;
};
