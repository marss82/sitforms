package cz.fel.cvut.sitforms.service;

public interface NotificationService {

    void addingToGroupNotification(Long userId, Long groupId);

    void addingToForm(Long userId, Long formId);

    void fillingFormNotification(Long creatorId, Long userId);
}

