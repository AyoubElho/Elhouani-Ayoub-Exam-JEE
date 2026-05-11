package org.example.gestionlocationbackend.entity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Set;

public class JwtUtil {

    private static final String SECRET =
            "mySecretKeymySecretKeymySecretKey";

    private static final long EXPIRATION_TIME =
            1000 * 60 * 60 * 24;

    private static final Set<String> ROLES =
            Set.of(
                    "ROLE_CLIENT",
                    "ROLE_EMPLOYE",
                    "ROLE_ADMIN"
            );

    private static final SecretKey KEY =
            Keys.hmacShaKeyFor(
                    SECRET.getBytes()
            );

    public static String generateToken(
            String username,
            String role
    ){

        return Jwts.builder()

                .setSubject(username)

                .claim("role", normalizeRole(role))

                .setIssuedAt(new Date())

                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                + EXPIRATION_TIME
                        )
                )

                .signWith(KEY, SignatureAlgorithm.HS256)

                .compact();
    }

    public static String getUsername(
            String token
    ){

        return getClaims(token)

                .getSubject();
    }

    public static String getRole(
            String token
    ){

        return normalizeRole(
                getClaims(token).get("role", String.class)
        );
    }

    public static String normalizeRole(
            String role
    ){

        String normalizedRole =
                role == null || role.isBlank()
                        ? "ROLE_CLIENT"
                        : role.trim().toUpperCase();

        if(!normalizedRole.startsWith("ROLE_")){
            normalizedRole = "ROLE_" + normalizedRole;
        }

        if(!ROLES.contains(normalizedRole)){
            throw new IllegalArgumentException(
                    "Role invalide"
            );
        }

        return normalizedRole;
    }

    private static Claims getClaims(
            String token
    ){

        return Jwts.parserBuilder()

                .setSigningKey(KEY)

                .build()

                .parseClaimsJws(token)

                .getBody();
    }
}
