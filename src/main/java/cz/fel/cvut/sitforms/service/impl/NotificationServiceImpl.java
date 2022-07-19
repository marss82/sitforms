package cz.fel.cvut.sitforms.service.impl;

import cz.fel.cvut.sitforms.model.FormEntity;
import cz.fel.cvut.sitforms.model.GroupEntity;
import cz.fel.cvut.sitforms.model.Notification;
import cz.fel.cvut.sitforms.model.UserEntity;
import cz.fel.cvut.sitforms.repository.FormRepository;
import cz.fel.cvut.sitforms.repository.GroupRepository;
import cz.fel.cvut.sitforms.repository.NotificationRepository;
import cz.fel.cvut.sitforms.repository.UserRepository;
import cz.fel.cvut.sitforms.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final GroupRepository groupRepository;

    private final UserRepository userRepository;

    private final FormRepository formRepository;


    @Override
    @Transactional
    public void addingToGroupNotification(Long userId, Long groupId) {
        UserEntity myUser = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        GroupEntity myGroup = groupRepository.findById(groupId).orElseThrow(() -> new EntityNotFoundException("Group not found"));

        Notification notification = new Notification();
        notification.setText("You have been added to group " + myGroup.getName());
        notification.setNotifiedUser(myUser);
        Notification save = notificationRepository.save(notification);

        myUser.getNotifications().add(save);

        userRepository.save(myUser);
    }

    @Override
    public void addingToForm(Long userId, Long formId) {
        UserEntity myUser = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        FormEntity form = formRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        Notification notification = new Notification();
        notification.setText("You have been added to private form " + form.getFormName() + " by user " + form.getUser().getName() + " " + form.getUser().getSurname());
        notification.setNotifiedUser(myUser);

        Notification save = notificationRepository.save(notification);

        myUser.getNotifications().add(save);

        userRepository.save(myUser);

    }

    @Override
    public void fillingFormNotification(Long formId, Long userId) {
        FormEntity filledForm = formRepository.findById(formId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        UserEntity userFilledForm = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));

        Notification notification = new Notification();
        notification.setText("User " + userFilledForm.getName() + " " +
                userFilledForm.getSurname() + " has filled your form: " + filledForm.getFormName());
        notification.setNotifiedUser(filledForm.getUser());

        Notification save = notificationRepository.save(notification);

        filledForm.getUser().getNotifications().add(save);

        userRepository.save(filledForm.getUser());
    }
}
