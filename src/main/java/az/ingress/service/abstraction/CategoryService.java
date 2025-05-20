package az.ingress.service.abstraction;

import az.ingress.model.request.CreateParentCategory;
import az.ingress.model.request.UpdateCategoryRequest;
import az.ingress.model.response.CategoryResponse;

import javax.validation.Valid;
import java.util.List;

public interface CategoryService {

    List<CategoryResponse> getAllCategories();

    void updateCategory(Long userId, @Valid UpdateCategoryRequest categoryRequest, Long categoryId);

    void deleteCategory(Long userId, Long categoryId);

    void createCategory(Long userId, @Valid CreateParentCategory createCategory);
}
