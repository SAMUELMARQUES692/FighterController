package dev.samuel.FightController.service;

import dev.samuel.FightController.domain.Category;
import dev.samuel.FightController.domain.Fighter;
import dev.samuel.FightController.exception.CategoryNotFoundException;
import dev.samuel.FightController.exception.FighterNotFoundException;
import dev.samuel.FightController.mapper.FighterMapper;
import dev.samuel.FightController.repository.CategoryRepository;
import dev.samuel.FightController.repository.FighterRepository;
import dev.samuel.FightController.request.FighterRequest;
import dev.samuel.FightController.response.FighterResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FighterService {

    private final FighterRepository fighterRepository;
    private final CategoryRepository categoryRepository;
    private final FighterMapper fighterMapper;


    @Transactional
    public FighterResponse create(FighterRequest fighterRequest) {
        Fighter newFighter = fighterMapper.toFighter(fighterRequest);

        // Busca as categorias completas do banco
        List<Category> categories = fighterRequest.categoryIds().stream()
                .map(id -> categoryRepository.findById(id)
                        .orElseThrow(() -> new CategoryNotFoundException(id)))
                .toList();

        // seta e persiste no banco de dados
        newFighter.setCategories(categories);
        Fighter savedFighter = fighterRepository.save(newFighter);
        return fighterMapper.toFighterResponse(savedFighter);
    }

    public List<FighterResponse> findAll() {
        List<Fighter> fighters = fighterRepository.findAll();
        return fighters.stream()
                .map(fighterMapper::toFighterResponse)
                .toList();
    }

    public FighterResponse findById(UUID id) {
        return fighterRepository.findById(id)
                .map(fighterMapper::toFighterResponse)
                .orElseThrow(() -> new FighterNotFoundException(id));
    }

    public FighterResponse uptade(FighterRequest request, UUID id) {
        Fighter fighterExist = fighterRepository.findById(id)
                .orElseThrow(() -> new FighterNotFoundException(id));

        if (fighterExist != null) {
            fighterExist.setName(request.name());
            fighterExist.setHeight(request.height());
            fighterExist.setBirthDate(request.birthDate());
            fighterExist.setWeight(request.weight());
            fighterExist.setFightCompetitions(request.fightCompetitions());
            fighterExist.setDescription(request.description());
        }
        return null;
        }


    public void delete(UUID id) {
        Fighter fighterExist = fighterRepository.findById(id)
                .orElseThrow(() -> new FighterNotFoundException(id));
        fighterRepository.deleteById(id);
    }




}
