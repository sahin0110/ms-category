package az.ingress.service.concrete;

import az.ingress.dao.entity.CategoryEntity;
import az.ingress.dao.repository.CategoryRepository;
import az.ingress.exception.AlreadyExistsException;
import az.ingress.exception.NotFoundException;
import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.request.CreateParentCategory;
import az.ingress.model.request.CreateSubCategoryRequest;
import az.ingress.model.request.UpdateCategoryRequest;
import az.ingress.model.response.CategoryResponse;
import az.ingress.model.response.PageableResponse;
import az.ingress.service.abstraction.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static az.ingress.exception.ErrorMessage.ALREADY_EXISTS;
import static az.ingress.exception.ErrorMessage.CATEGORY_NOT_FOUND;
import static az.ingress.mapper.CategoryMapper.CATEGORY_MAPPER;
import static az.ingress.mapper.PageableMapper.PAGEABLE_MAPPER;

@Service
@RequiredArgsConstructor
public class CategoryServiceHandler implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void createParentCategory(CreateParentCategory createCategory) {
        existsByName(createCategory.getName());

        var category = CategoryEntity.builder()
                .name(createCategory.getName())
                .build();
        categoryRepository.save(category);
    }

    @Override
    public void createSubCategory(CreateSubCategoryRequest request) {
        existsByName(request.getName());
        findCategoryGetByIdOrThrow(request.getParentId());
        var category = CATEGORY_MAPPER.toCategoryEntity(request);

        categoryRepository.save(category);
    }

    @Override
    public PageableResponse<CategoryResponse> getAllSubCategories(PageCriteria pageCriteria, Long parentId) {
        var categoryPage = categoryRepository.findAllByParentId(parentId,
                PAGEABLE_MAPPER.toPageRequest(pageCriteria));

        return PAGEABLE_MAPPER.buildPageableResponse(categoryPage,
                CATEGORY_MAPPER::toCategoryResponse);
    }

    @Override
    public PageableResponse<CategoryResponse> getAllCategories(PageCriteria pageCriteria) {
        var categoryPage = categoryRepository.findAll(
                PAGEABLE_MAPPER.toPageRequest(pageCriteria)
        );

        return PAGEABLE_MAPPER.buildPageableResponse(
                categoryPage, CATEGORY_MAPPER::toCategoryResponse
        );
    }

    @Override
    public CategoryResponse getCategoryById(Long categoryId) {
        var category = findCategoryGetByIdOrThrow(categoryId);
        return CATEGORY_MAPPER.toCategoryResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(UpdateCategoryRequest categoryRequest, Long categoryId) {
        var category = findCategoryGetByIdOrThrow(categoryId);
        CATEGORY_MAPPER.updateCategory(category, categoryRequest);

        var save = categoryRepository.save(category);
        return CATEGORY_MAPPER.toCategoryResponse(save);
    }

    @Override
    @Transactional
    public void deleteCategory(Long categoryId) {
        var category = findCategoryGetByIdOrThrow(categoryId);
        categoryRepository.deleteSubCategoriesByParent(category);
        categoryRepository.delete(category);
    }

    private CategoryEntity findCategoryGetByIdOrThrow(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND.getMessage()));
    }

    private void existsByName(String categoryName) {
        if (categoryRepository.existsByName(categoryName)) {
            throw new AlreadyExistsException(ALREADY_EXISTS.getMessage());
        }
    }
}
