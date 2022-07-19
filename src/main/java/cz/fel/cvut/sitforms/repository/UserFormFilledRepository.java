package cz.fel.cvut.sitforms.repository;

import cz.fel.cvut.sitforms.model.FormEntity;
import cz.fel.cvut.sitforms.model.UserEntity;
import cz.fel.cvut.sitforms.model.UserFormsFilled;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFormFilledRepository extends JpaRepository<UserFormsFilled, Long> {
    List<UserFormsFilled> findAllByForm(FormEntity formEntity);

    List<UserFormsFilled> findAllByUser(UserEntity user);
}
