package az.ingress.service.concrete;

import az.ingress.dao.repository.CategoryRepository;
import az.ingress.exception.ConflictException;
import az.ingress.exception.NotFoundException;
import az.ingress.model.request.CreateSubCategoryRequest;
import az.ingress.model.response.CategoryResponse;
import az.ingress.service.abstraction.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static az.ingress.exception.ErrorMessage.CATEGORY_NAME_ALREADY_EXISTS;
import static az.ingress.exception.ErrorMessage.CATEGORY_NOT_FOUND;
import static az.ingress.mapper.CategoryMapper.CATEGORY_MAPPER;
import static az.ingress.model.enums.Status.ACTIVE;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceHandler implements SubCategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void createSubCategory(Long userId, CreateSubCategoryRequest request) {
        ensureNotDuplicateCategory(request.getName());
        var category = CATEGORY_MAPPER.toSubCategoryEntity(userId, request);
        categoryRepository.save(category);
    }

    @Override
    public List<CategoryResponse> getAllSubCategories(Long parentId) {
        var category = categoryRepository.findById(parentId)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND.getCode(), parentId));

        var allSubCategoriesByParentAndStatus = categoryRepository.findAllSubCategoriesByParentAndStatus(category, ACTIVE);
        return allSubCategoriesByParentAndStatus.stream()
                .map(CATEGORY_MAPPER::toCategoryResponse)
                .toList();
    }

    private void ensureNotDuplicateCategory(String categoryName) {
        categoryRepository.findByName(categoryName)
                .ifPresent(existingCategory -> {
                    throw new ConflictException(CATEGORY_NAME_ALREADY_EXISTS.getCode(), categoryName);
                });
    }
}
