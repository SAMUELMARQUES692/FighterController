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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ScopeService scopeService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
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

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .toList();
    }

    public UserResponse findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return userMapper.toUserResponse(user);
    }

    public void delete(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        userRepository.deleteById(id);
    }

    @Transactional
    public UserResponse update(UserRequest request, Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        User updateUser = userMapper.toEntity(request);

        updateUser.setId(user.getId());
        updateUser.setPassword(passwordEncoder.encode(request.password()));
        updateUser.setScopes(request.scopes().stream()
                .map(scopeService::findById)
                .toList());

        User saveUser = userRepository.save(updateUser);
        return userMapper.toUserResponse(saveUser);
    }


}
