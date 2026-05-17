package dev.samuel.FightController.domain;

import dev.samuel.FightController.enums.FightCompetition;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "fighters")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Fighter {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal height;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal weight;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "fighter_competitions", joinColumns = @JoinColumn(name = "fighter_id"))
    @Column(name = "competition")
    private List<FightCompetition> fightCompetitions;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "fighter_category",
            joinColumns = @JoinColumn(name = "fighter_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
