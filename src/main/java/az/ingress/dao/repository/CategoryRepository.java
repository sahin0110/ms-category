package az.ingress.dao.repository;

import az.ingress.dao.entity.CategoryEntity;
import az.ingress.model.enums.Status;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    List<CategoryEntity> findAllByStatus(Status status);

    Optional<CategoryEntity> findByName(String categoryName);

    List<CategoryEntity> findAllCategoriesByParentIdAndStatus(Long parentId, Status status);

    List<CategoryEntity> findAllCategoriesByParentId(Long parentId);
}
