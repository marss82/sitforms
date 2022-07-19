package cz.fel.cvut.sitforms.repository;

import cz.fel.cvut.sitforms.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
