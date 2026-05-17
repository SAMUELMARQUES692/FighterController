package dev.samuel.FightController.mapper;

import dev.samuel.FightController.domain.Category;
import dev.samuel.FightController.request.CategoryRequest;
import dev.samuel.FightController.response.CategoryResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    // converte CategoryRequest → entidade Category
    Category toCategory(CategoryRequest categoryRequest);

    // converte entidade Category → CategoryResponse
    CategoryResponse toCategoryResponse(Category category);

}
