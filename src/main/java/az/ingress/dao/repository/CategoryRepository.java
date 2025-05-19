package az.ingress.dao.repository;

import az.ingress.dao.entity.CategoryEntity;
import az.ingress.model.enums.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByStatus(Status status);

    List<CategoryEntity> findAllSubCategoriesByParentAndStatus(CategoryEntity parent, Status status);

    Optional<CategoryEntity> findByName(String categoryName);

    List<CategoryEntity> findAllSubCategoriesByParent(CategoryEntity parent);
}
