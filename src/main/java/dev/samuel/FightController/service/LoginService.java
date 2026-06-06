package dev.samuel.FightController.service;

import dev.samuel.FightController.domain.Scope;
import dev.samuel.FightController.domain.User;
import dev.samuel.FightController.exception.UserOrPasswordIncorectException;
import dev.samuel.FightController.repository.UserRepository;
import dev.samuel.FightController.request.LoginRequest;
import dev.samuel.FightController.response.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtEncoder jwtEncoder;

    public LoginResponse login(LoginRequest request) {

        Optional<User> optUser = userRepository.findByUsername(request.username());
        if (optUser.isEmpty() || !isPasswordCorrect(request.password(), optUser.get().getPassword())) {
            throw new UserOrPasswordIncorectException(request.password(), request.username());
        }

        User savedUser = optUser.get();
        List<String> scopes = savedUser.getScopes().stream()
                .map(Scope::getName)
                .toList();
        long expiresIn = 600L;

        JwtClaimsSet jwt = JwtClaimsSet.builder()
                .issuer("fightController-api")
                .subject(savedUser.getName())
                .expiresAt(Instant.now().plusSeconds(expiresIn))
                .issuedAt(Instant.now())
                .claim("username", savedUser.getUsername())
                .claim("scope", String.join(" ", scopes))
                .build();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(jwt)).getTokenValue();

        return LoginResponse.builder()
                .accessToken(token)
                .expiresIn(expiresIn)
                .build();

    }

    private boolean isPasswordCorrect(String password, String savePassword) {
        return passwordEncoder.matches(password, savePassword);
    }

}
