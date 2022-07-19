package cz.fel.cvut.sitforms.repository;

import cz.fel.cvut.sitforms.model.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
}
