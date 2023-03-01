package com.github.daianaegermichels.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.github.daianaegermichels.api.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generatedToken(User user) {
        try{
            var algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Medical Appointment")
                    .withSubject(user.getLogin())
                    .withExpiresAt(dateExpiration())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error: generated token :/", exception);
        }
    }

    private Instant dateExpiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
