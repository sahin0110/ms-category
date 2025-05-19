package az.ingress.service.abstraction;

import az.ingress.model.request.CreateSubCategoryRequest;
import az.ingress.model.response.CategoryResponse;

import javax.validation.Valid;
import java.util.List;

public interface SubCategoryService {

    void createSubCategory(Long userId, @Valid CreateSubCategoryRequest request);

    List<CategoryResponse> getAllSubCategories(Long parentId);
}
