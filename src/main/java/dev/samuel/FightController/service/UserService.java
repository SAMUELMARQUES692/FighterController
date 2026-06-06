package dev.samuel.FightController.service;

import dev.samuel.FightController.domain.Scope;
import dev.samuel.FightController.domain.User;
import dev.samuel.FightController.exception.ResourceAlreadyExistException;
import dev.samuel.FightController.mapper.UserMapper;
import dev.samuel.FightController.repository.UserRepository;
import dev.samuel.FightController.request.UserRequest;
import dev.samuel.FightController.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ScopeService scopeService;
    private final PasswordEncoder passwordEncoder;

    public UserResponse create(UserRequest userRequest) {

        if (userRepository.existsByUsername(userRequest.username())) {
            throw new ResourceAlreadyExistException(userRequest.username());
        }

        List<Scope> scopes = userRequest.scopes().stream()
                .map(scopeService::findById)
                .toList();

        User newUser = userMapper.toEntity(userRequest);
        newUser.setScopes(scopes);
        newUser.setPassword(passwordEncoder.encode(userRequest.password()));
        User saveUser = userRepository.save(newUser);
        return userMapper.toUserResponse(saveUser);
    }


}
