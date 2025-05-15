package az.ingress.mapper;

import az.ingress.dao.entity.CategoryEntity;
import az.ingress.model.request.CreateSubCategoryRequest;
import az.ingress.model.request.UpdateCategoryRequest;
import az.ingress.model.response.CategoryResponse;

public enum CategoryMapper {
    CATEGORY_MAPPER;

    public CategoryEntity toCategoryEntity(CreateSubCategoryRequest request) {
        return CategoryEntity.builder()
                .parent(CategoryEntity.builder().id(request.getParentId()).build())
                .name(request.getName())
                .build();
    }

    public CategoryResponse toCategoryResponse(CategoryEntity categoryEntity) {
        return CategoryResponse.builder()
                .id(categoryEntity.getId())
                .name(categoryEntity.getName())
                .parentId(categoryEntity.getParent() != null ? categoryEntity.getParent().getId() : null)
                .build();
    }

    public void updateCategory(CategoryEntity category, UpdateCategoryRequest request) {
        if (request.getName() != null) {
            category.setName(request.getName());
        }
    }
}
