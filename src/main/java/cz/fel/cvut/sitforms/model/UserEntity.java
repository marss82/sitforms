package cz.fel.cvut.sitforms.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Accessors(chain = true)
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String password;
    @ManyToOne
    @JoinColumn(name="role_id")
    private RoleEntity roleEntity;
    private String name;
    private String surname;
    @OneToMany(mappedBy="user")
    private List<FormEntity> myFormEntities;
    @OneToMany(mappedBy = "notifiedUser")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user")
    private List<UserFormsFilled> filledForms;

    @ManyToMany
    @JoinTable(
            name = "user_assignedGroups",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<GroupEntity> assignedGroups;

    @OneToMany(mappedBy = "author")
    private List<GroupEntity> createdGroups;

    @OneToOne(mappedBy = "userEntity", cascade = CascadeType.ALL)
    private Subscription subscription;
}
