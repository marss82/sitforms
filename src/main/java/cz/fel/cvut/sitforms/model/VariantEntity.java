package cz.fel.cvut.sitforms.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class VariantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int clickNumbers = 0;
    @ManyToOne
    @JoinColumn(name="questionId", nullable=false)
    private QuestionEntity question;
}
