package az.ingress.service.abstraction;

import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.request.CreateParentCategory;
import az.ingress.model.request.CreateSubCategoryRequest;
import az.ingress.model.request.UpdateCategoryRequest;
import az.ingress.model.response.CategoryResponse;
import az.ingress.model.response.PageableResponse;

import javax.validation.Valid;

public interface CategoryService {
    void createSubCategory(@Valid CreateSubCategoryRequest request);

    PageableResponse<CategoryResponse> getAllSubCategories(PageCriteria pageCriteria, Long parentId);

    PageableResponse<CategoryResponse> getAllCategories(PageCriteria pageCriteria);

    CategoryResponse getCategoryById(Long categoryId);

    CategoryResponse updateCategory(@Valid UpdateCategoryRequest categoryRequest, Long categoryId);

    void deleteCategory(Long categoryId);

    void createParentCategory(@Valid CreateParentCategory createCategory);
}
