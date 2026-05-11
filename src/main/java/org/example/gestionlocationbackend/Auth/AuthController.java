package org.example.gestionlocationbackend.Auth;

import lombok.RequiredArgsConstructor;

import org.example.gestionlocationbackend.entity.AppUser;
import org.example.gestionlocationbackend.entity.JwtUtil;
import org.example.gestionlocationbackend.repository.AppUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto
        .password.PasswordEncoder;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final AppUserRepository
            userRepository;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public AuthResponse register(
            @RequestBody RegisterRequest request
    ){

        if(
                userRepository
                        .findByUsername(request.username())
                        .isPresent()
        ){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Username existe deja"
            );
        }

        AppUser user =
                AppUser.builder()
                        .username(request.username())
                        .password(
                                passwordEncoder.encode(
                                        request.password()
                                )
                        )
                        .role("ROLE_CLIENT")
                        .build();

        return buildAuthResponse(
                userRepository.save(user)
        );
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request
    ){

        AppUser appUser =
                userRepository
                        .findByUsername(
                                request.username()
                        )
                        .orElseThrow(() ->
                                new ResponseStatusException(
                                        HttpStatus.UNAUTHORIZED,
                                        "Bad credentials"
                                )
                        );

        if(!passwordEncoder.matches(
                request.password(),
                appUser.getPassword()
        )){
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Bad credentials"
            );
        }

        return buildAuthResponse(appUser);
    }

    private AuthResponse buildAuthResponse(
            AppUser appUser
    ){

        String role =
                JwtUtil.normalizeRole(appUser.getRole());

        return new AuthResponse(
                appUser.getId(),
                appUser.getUsername(),
                role,
                JwtUtil.generateToken(
                        appUser.getUsername(),
                        role
                )
        );
    }

    public record RegisterRequest(
            String username,
            String password
    ) {}

    public record LoginRequest(
            String username,
            String password
    ) {}

    public record AuthResponse(
            Long id,
            String username,
            String role,
            String token
    ) {}
}
