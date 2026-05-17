package dev.samuel.FightController.repository;

import dev.samuel.FightController.domain.Fighter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FighterRepository extends JpaRepository<Fighter, UUID> {
}
