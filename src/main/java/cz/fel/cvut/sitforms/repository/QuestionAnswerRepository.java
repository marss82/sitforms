package cz.fel.cvut.sitforms.repository;

import cz.fel.cvut.sitforms.model.QuestionAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionAnswerRepository extends JpaRepository<QuestionAnswer, Long> {
}
