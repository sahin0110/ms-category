package az.ingress.controller;

import az.ingress.model.request.CreateSubCategoryRequest;
import az.ingress.model.response.CategoryResponse;
import az.ingress.service.abstraction.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.util.List;

import static az.ingress.model.constant.HeaderConstants.USER_ID;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/v1/sub-categories")
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void createSubCategory(@RequestHeader(USER_ID) Long userId,
                                  @RequestBody @Valid CreateSubCategoryRequest request) {
        subCategoryService.createSubCategory(userId, request);
    }

    @GetMapping("/{categoryId}")
    public List<CategoryResponse> getSubCategoriesByParent(@PathVariable Long categoryId) {
        return subCategoryService.getAllSubCategories(categoryId);
    }
}
