package cz.fel.cvut.sitforms.repository;

import cz.fel.cvut.sitforms.model.FormEntity;
import cz.fel.cvut.sitforms.model.FormType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormRepository extends JpaRepository<FormEntity, Long> {
    List<FormEntity> findByUserId(Long authorId);

    List<FormEntity> findByFormType(FormType type);

}
