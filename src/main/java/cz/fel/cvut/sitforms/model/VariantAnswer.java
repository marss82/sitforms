package cz.fel.cvut.sitforms.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class VariantAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean answeredVariant;
    @ManyToOne
    @JoinColumn(name="question_answer_id", nullable=false)
    private QuestionAnswer questionAnswer;
}
