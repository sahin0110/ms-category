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
        validateCategoryNameUniqueness(request.getName());
        var subCategory = CATEGORY_MAPPER.toSubCategoryEntity(userId, request);
        categoryRepository.save(subCategory);
    }

    @Override
    public List<CategoryResponse> getAllSubCategories(Long parentId) {
        validateParentCategoryExists(parentId);
        return fetchSubCategoriesByParentId(parentId);
    }

    private void validateCategoryNameUniqueness(String categoryName) {
        categoryRepository.findByName(categoryName)
                .ifPresent(existingCategory -> {
                    throw new ConflictException(CATEGORY_NAME_ALREADY_EXISTS.getMessage(), categoryName);
                });
    }

    private void validateParentCategoryExists(Long parentId) {
        categoryRepository.findById(parentId)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND.getMessage(), parentId));
    }

    private List<CategoryResponse> fetchSubCategoriesByParentId(Long parentId) {
        return categoryRepository.findAllCategoriesByParentIdAndStatus(parentId, ACTIVE)
                .stream()
                .map(CATEGORY_MAPPER::toCategoryResponse)
                .toList();
    }
}