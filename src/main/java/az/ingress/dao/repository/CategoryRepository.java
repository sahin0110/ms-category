package az.ingress.dao.repository;

import az.ingress.dao.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long>, JpaRepository<CategoryEntity, Long> {
    Page<CategoryEntity> findAllByParentId(Long parentId, Pageable pageable);

    boolean existsByName(String name);

    void deleteSubCategoriesByParent(CategoryEntity parent);
}
