package cz.fel.cvut.sitforms.repository;

import cz.fel.cvut.sitforms.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByEmail(String email);

    UserEntity findByEmail(String email);

    boolean existsByUsername(String username);

    UserEntity findByUsername(String username);
}
