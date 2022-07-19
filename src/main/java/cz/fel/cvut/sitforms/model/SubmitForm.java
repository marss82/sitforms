package cz.fel.cvut.sitforms.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class SubmitForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="form_id", nullable=false)
    private FormEntity formEntity;

    @ManyToOne
    @JoinColumn(name="sender_id", nullable=false)
    private UserEntity sender;

    @OneToMany(mappedBy = "submitForm", cascade = CascadeType.ALL)
    private List<QuestionAnswer> questionAnswers = new ArrayList<>();
}
