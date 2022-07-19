package cz.fel.cvut.sitforms.repository;

import cz.fel.cvut.sitforms.model.SubmitForm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubmitFormRepository extends JpaRepository<SubmitForm, Long> {
}
