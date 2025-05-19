package az.ingress.service.concrete;

import az.ingress.dao.entity.CategoryEntity;
import az.ingress.dao.repository.CategoryRepository;
import az.ingress.exception.ConflictException;
import az.ingress.exception.NotFoundException;
import az.ingress.model.request.CreateParentCategory;
import az.ingress.model.request.UpdateCategoryRequest;
import az.ingress.model.response.CategoryResponse;
import az.ingress.service.abstraction.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.exception.ErrorMessage.CATEGORY_ALREADY_EXISTS;
import static az.ingress.exception.ErrorMessage.CATEGORY_NOT_FOUND;
import static az.ingress.mapper.CategoryMapper.CATEGORY_MAPPER;
import static az.ingress.model.enums.Status.ACTIVE;
import static az.ingress.model.enums.Status.INACTIVE;

@Service
@RequiredArgsConstructor
public class CategoryServiceHandler implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void createCategory(Long userId, CreateParentCategory createCategory) {
        ensureNotDuplicateCategory(createCategory.getName());

        var category = CATEGORY_MAPPER.toCategoryEntity(userId, createCategory);
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        var getAllCategories = categoryRepository.findAllByStatus(ACTIVE);

        return getAllCategories.stream()
                .map(CATEGORY_MAPPER::toCategoryResponse)
                .toList();
    }

    @Override
    public void updateCategory(Long userId, UpdateCategoryRequest categoryRequest, Long categoryId) {
        var category = findCategoryGetByIdOrThrow(categoryId);
        if (categoryRequest.getName() != null) {
            CATEGORY_MAPPER.updateCategory(userId, category, categoryRequest);
        }
        categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long userId, Long categoryId) {

        var category = findCategoryGetByIdOrThrow(categoryId);
        CATEGORY_MAPPER.deleteCategory(userId, category);
        var allSubCategories = categoryRepository.findAllSubCategoriesByParent(category);
        allSubCategories.forEach(subCategory -> subCategory.setStatus(INACTIVE));
        categoryRepository.saveAll(allSubCategories);
        categoryRepository.save(category);
    }

    private CategoryEntity findCategoryGetByIdOrThrow(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND.getCode(), categoryId));
    }

    private void ensureNotDuplicateCategory(String categoryName) {
        categoryRepository.findByName(categoryName)
                .ifPresent(category -> {
                    throw new ConflictException(CATEGORY_ALREADY_EXISTS.getCode(), category.getId());
                });
    }
}
