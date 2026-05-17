package dev.samuel.FightController.service;

import dev.samuel.FightController.domain.Category;
import dev.samuel.FightController.exception.CategoryNotFoundException;
import dev.samuel.FightController.mapper.CategoryMapper;
import dev.samuel.FightController.repository.CategoryRepository;
import dev.samuel.FightController.request.CategoryRequest;
import dev.samuel.FightController.response.CategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryResponse create(CategoryRequest categoryRequest) {
        Category newCategory = categoryMapper.toCategory(categoryRequest);
        Category saveCategory = categoryRepository.save(newCategory);
        return categoryMapper.toCategoryResponse(saveCategory);
    }

    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toCategoryResponse)
                .toList();
    }

    public CategoryResponse findById(UUID id) {
        return categoryRepository.findById(id)
                .map(categoryMapper::toCategoryResponse)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    public Category findEntityById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    @Transactional
    public CategoryResponse update(UUID id, CategoryRequest categoryRequest) {
        Category categoryExist = categoryRepository.findById(id)
                                    .orElseThrow(() -> new CategoryNotFoundException(id));
        if (categoryExist != null) {
            categoryExist.setName(categoryRequest.name());
            Category updatedCategory = categoryRepository.save(categoryExist);
            return categoryMapper.toCategoryResponse(updatedCategory);
        }
        return null;
    }

    public void delete(UUID id) {
        categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException(id));
        categoryRepository.deleteById(id);
    }


}
