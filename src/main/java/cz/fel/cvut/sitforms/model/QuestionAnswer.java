package cz.fel.cvut.sitforms.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class QuestionAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @OneToMany(mappedBy = "questionAnswer", cascade = CascadeType.ALL)
    private List<VariantAnswer> variantAnswers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="submit_form_id", nullable=false)
    private SubmitForm submitForm;

}
