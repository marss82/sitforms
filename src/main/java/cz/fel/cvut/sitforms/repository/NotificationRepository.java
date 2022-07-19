package cz.fel.cvut.sitforms.repository;

import cz.fel.cvut.sitforms.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
