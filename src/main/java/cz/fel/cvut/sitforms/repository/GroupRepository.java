package cz.fel.cvut.sitforms.repository;

import cz.fel.cvut.sitforms.model.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository  extends JpaRepository<GroupEntity, Long> {

    GroupEntity findByName(String nameOfGroup);

}
