package cz.fel.cvut.sitforms.repository;

import cz.fel.cvut.sitforms.model.VariantEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VariantRepository extends JpaRepository<VariantEntity, Long> {
}
