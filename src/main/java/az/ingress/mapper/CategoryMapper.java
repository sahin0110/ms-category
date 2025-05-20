package az.ingress.mapper;

import az.ingress.dao.entity.CategoryEntity;
import az.ingress.model.request.CreateParentCategory;
import az.ingress.model.request.CreateSubCategoryRequest;
import az.ingress.model.request.UpdateCategoryRequest;
import az.ingress.model.response.CategoryResponse;

import static az.ingress.model.enums.Status.ACTIVE;
import static az.ingress.model.enums.Status.INACTIVE;

public enum CategoryMapper {
    CATEGORY_MAPPER;

    public CategoryEntity toSubCategoryEntity(Long userId, CreateSubCategoryRequest request) {
        return CategoryEntity.builder()
                .parentId(request.getParentId())
                .name(request.getName())
                .status(ACTIVE)
                .userId(userId)
                .build();
    }

    public CategoryEntity toCategoryEntity(Long userId, CreateParentCategory request) {
        return CategoryEntity.builder()
                .name(request.getName())
                .status(ACTIVE)
                .userId(userId)
                .build();
    }

    public CategoryResponse toCategoryResponse(CategoryEntity categoryEntity) {
        return CategoryResponse.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .parentId(categoryEntity.getParentId() != null ? categoryEntity.getParentId() : null)
                .build();
    }

    public void updateCategory(Long userId, CategoryEntity category, UpdateCategoryRequest request) {
        category.setUserId(userId);
        category.setName(request.getName());
    }

    public void deleteCategory(Long userId, CategoryEntity category) {
        category.setStatus(INACTIVE);
        category.setUserId(userId);
    }
}
