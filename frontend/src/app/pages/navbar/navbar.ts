import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { AuthService, UserRole } from '../../service/AuthService';

@Component({
  selector: 'app-navbar',
  imports: [CommonModule, RouterLink, RouterLinkActive],
  templateUrl: './navbar.html',
  styleUrl: './navbar.css',
})
export class Navbar {
  private readonly managerRoles: UserRole[] = [
    'ROLE_EMPLOYE',
    'ROLE_ADMIN',
  ];

  constructor(private readonly authService: AuthService) {}

  isAuthenticated(): boolean {
    return this.authService.isAuthenticated();
  }

  canManage(): boolean {
    return this.authService.hasAnyRole(this.managerRoles);
  }

  username(): string | null {
    return this.authService.getUsername();
  }

  role(): UserRole | null {
    return this.authService.getRole();
  }

  logout(): void {
    this.authService.logout();
  }
}
