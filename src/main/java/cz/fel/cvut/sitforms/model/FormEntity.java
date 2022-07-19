package cz.fel.cvut.sitforms.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class FormEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String formName;
    private String category;
    private String description;
    private int viewsCount = 0;
    private boolean instantStatistics;
    @Enumerated(EnumType.STRING)
    private FormType formType;
    @OneToMany(mappedBy = "form")
    private List<UserFormsFilled> filledForms;
    @ManyToMany(mappedBy = "forms", cascade = CascadeType.ALL)
    private List<Category> categories;
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private UserEntity user;
    @OneToMany(mappedBy = "formEntity", cascade = CascadeType.ALL)
    private List<QuestionEntity> questionEntities;

    @OneToMany(mappedBy = "formEntity", cascade = CascadeType.ALL)
    private List<SubmitForm> submitForms;
}
