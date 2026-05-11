package org.example.gestionlocationbackend.security;

import lombok.RequiredArgsConstructor;
import org.example.gestionlocationbackend.repository.AppUserRepository;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation
        .web.builders.HttpSecurity;

import org.springframework.security.config
        .annotation.web.configuration
        .EnableWebSecurity;

import org.springframework.security.config.http
        .SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt
        .BCryptPasswordEncoder;

import org.springframework.security.crypto
        .password.PasswordEncoder;

import org.springframework.security.core.userdetails
        .User;

import org.springframework.security.core.userdetails
        .UserDetailsService;

import org.springframework.security.core.userdetails
        .UsernameNotFoundException;

import org.springframework.security.web
        .SecurityFilterChain;

import org.springframework.security.web.authentication
        .UsernamePasswordAuthenticationFilter;

import org.springframework.web.cors
        .CorsConfiguration;

import org.springframework.web.cors
        .CorsConfigurationSource;

import org.springframework.web.cors
        .UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter
            jwtAuthenticationFilter;

    @Bean
    PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(
            AppUserRepository appUserRepository
    ){

        return username ->
                appUserRepository
                        .findByUsername(username)
                        .map(appUser ->
                                User.withUsername(
                                                appUser.getUsername()
                                        )
                                        .password(
                                                appUser.getPassword()
                                        )
                                        .authorities(
                                                appUser.getRole()
                                        )
                                        .build()
                        )
                        .orElseThrow(() ->
                                new UsernameNotFoundException(
                                        "Utilisateur introuvable"
                                )
                        );
    }

    @Bean
    SecurityFilterChain
    securityFilterChain(
            HttpSecurity http
    ) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .cors(cors -> {})

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .headers(headers ->
                        headers.frameOptions(frame ->
                                frame.sameOrigin()
                        )
                )

                .formLogin(form -> form.disable())

                .httpBasic(httpBasic -> httpBasic.disable())

                .authorizeHttpRequests(auth ->

                auth

                        .requestMatchers(
                                HttpMethod.OPTIONS,
                                "/**"
                        ).permitAll()

                        .requestMatchers(
                                "/auth/login",
                                "/auth/register",
                                "/auth/**",
                                "/swagger-ui/**",
                                "/v3/api-docs/**",
                                "/h2-console/**"
                        ).permitAll()

                        // CLIENT
                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/vehicules/**"
                        )

                        .hasAnyRole(
                                "CLIENT",
                                "EMPLOYE",
                                "ADMIN"
                        )

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/agences/**"
                        )

                        .hasAnyRole(
                                "CLIENT",
                                "EMPLOYE",
                                "ADMIN"
                        )

                        // EMPLOYE
                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/vehicules/**"
                        )

                        .hasAnyRole(
                                "EMPLOYE",
                                "ADMIN"
                        )

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/agences/**"
                        )

                        .hasAnyRole(
                                "EMPLOYE",
                                "ADMIN"
                        )

                        // ADMIN
                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/vehicules/**"
                        )

                        .hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/agences/**"
                        )

                        .hasRole("ADMIN")

                        .requestMatchers(
                                "/api/**"
                        )

                        .hasAnyRole(
                                "EMPLOYE",
                                "ADMIN"
                        )

                        .anyRequest()
                        .authenticated()
        )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){

        CorsConfiguration configuration =
                new CorsConfiguration();

        configuration.setAllowedOrigins(
                List.of(
                        "http://localhost:4200",
                        "http://127.0.0.1:4200"
                )
        );

        configuration.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "PATCH",
                        "DELETE",
                        "OPTIONS"
                )
        );

        configuration.setAllowedHeaders(
                List.of(
                        "Authorization",
                        "Content-Type"
                )
        );

        configuration.setExposedHeaders(
                List.of("Authorization")
        );

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();

        source.registerCorsConfiguration(
                "/**",
                configuration
        );

        return source;
    }
}
