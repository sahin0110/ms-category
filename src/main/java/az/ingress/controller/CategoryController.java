package az.ingress.controller;

import az.ingress.model.criteria.PageCriteria;
import az.ingress.model.request.CreateParentCategory;
import az.ingress.model.request.CreateSubCategoryRequest;
import az.ingress.model.request.UpdateCategoryRequest;
import az.ingress.model.response.CategoryResponse;
import az.ingress.model.response.PageableResponse;
import az.ingress.service.abstraction.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/parentCategory")
    public void createCategory(@RequestBody @Valid CreateParentCategory createCategory) {
        categoryService.createParentCategory(createCategory);
    }

    @PostMapping("/subCategory")
    public void createSubCategory(@RequestBody @Valid CreateSubCategoryRequest request) {
        categoryService.createSubCategory(request);
    }
    @GetMapping
    public PageableResponse<CategoryResponse> getAllCategories(PageCriteria pageCriteria) {
        return categoryService.getAllCategories(pageCriteria);
    }

    @GetMapping("/{parentId}/subCategories")
    public PageableResponse<CategoryResponse> getSubCategoriesByParent(PageCriteria pageCriteria,
                                                                  @PathVariable Long parentId) {
        return categoryService.getAllSubCategories(pageCriteria, parentId);
    }

    @GetMapping("/{categoryId}")
    public CategoryResponse getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PutMapping("/{categoryId}")
    public CategoryResponse updateCategory(@RequestBody @Valid UpdateCategoryRequest categoryRequest,
                                           @PathVariable Long categoryId) {
        return categoryService.updateCategory(categoryRequest, categoryId);
    }

    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);
    }
}