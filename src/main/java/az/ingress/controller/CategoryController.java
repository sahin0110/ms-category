package az.ingress.controller;

import az.ingress.model.request.CreateParentCategory;
import az.ingress.model.request.UpdateCategoryRequest;
import az.ingress.model.response.CategoryResponse;
import az.ingress.service.abstraction.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static az.ingress.model.constant.HeaderConstants.USER_ID;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createCategory(@RequestHeader(USER_ID) Long userId,
                               @RequestBody @Valid CreateParentCategory createCategory) {
        categoryService.createCategory(userId, createCategory);
    }

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PutMapping("/{categoryId}")
    @ResponseStatus(NO_CONTENT)
    public void updateCategory(@RequestHeader(USER_ID) Long userId,
                               @RequestBody @Valid UpdateCategoryRequest categoryRequest,
                               @PathVariable Long categoryId) {
        categoryService.updateCategory(userId, categoryRequest, categoryId);
    }

    @DeleteMapping("/{categoryId}")
    @ResponseStatus(NO_CONTENT)
    public void deleteCategory(@RequestHeader(USER_ID) Long userId,
                               @PathVariable Long categoryId) {
        categoryService.deleteCategory(userId, categoryId);
    }
}