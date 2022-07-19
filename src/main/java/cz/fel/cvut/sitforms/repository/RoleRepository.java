package cz.fel.cvut.sitforms.repository;

import cz.fel.cvut.sitforms.model.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String role);
}
