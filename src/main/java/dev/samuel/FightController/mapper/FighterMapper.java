package dev.samuel.FightController.mapper;

import dev.samuel.FightController.domain.Category;
import dev.samuel.FightController.domain.Fighter;
import dev.samuel.FightController.enums.FightCompetition;
import dev.samuel.FightController.request.FighterRequest;
import dev.samuel.FightController.response.FighterResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface FighterMapper {

    // converte FighterRequest → entidade Fighter
    @Mapping(source = "categoryIds", target = "categories", qualifiedByName = "idsToCategories")
    Fighter toFighter(FighterRequest fighterRequest);

    // toFighterResponse — converte entidade Fighter → FighterResponse
    @Mapping(source = "fightCompetitions", target = "fightCompetitions", qualifiedByName = "enumListToStringList")
    @Mapping(source = "categories", target = "categories", qualifiedByName = "categoryListToStringList")
    FighterResponse toFighterResponse(Fighter fighter);

    // mapEnumList — converte lista de enums para lista de Strings
    @Named("enumListToStringList")
    default List<String> mapEnumList(List<FightCompetition> competitions) {
        if (competitions == null) return null;
        return competitions.stream()
                .map(FightCompetition::name)
                .toList();
    }

    // mapCategoryList — converte lista de entidades para lista de nomes de categorias
    @Named("categoryListToStringList")
    default List<String> mapCategoryList(List<Category> categories) {
        if (categories == null) return null;
        return categories.stream()
                .map(Category::getName)
                .toList();
    }

    // mapIdsToCategories — converte lista de ids para lista de entidades
    @Named("idsToCategories")
    default List<Category> mapIdsToCategories(List<UUID> ids) {
        if (ids == null) return null;
        return ids.stream()
                .map(id -> {
                    Category category = new Category();
                    category.setId(id);
                    return category;
                })
                .toList();
    }

}
