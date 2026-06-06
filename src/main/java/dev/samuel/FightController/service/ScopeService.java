package dev.samuel.FightController.service;

import dev.samuel.FightController.domain.Scope;
import dev.samuel.FightController.repository.ScopeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScopeService {

    private final ScopeRepository scopeRepository;

    public Scope findById(Long id) {
        return scopeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Scope not found with id: " + id));
    }
}
