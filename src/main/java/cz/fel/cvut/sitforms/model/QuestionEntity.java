package cz.fel.cvut.sitforms.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class QuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int totalClickNumbers = 0;

    @Enumerated(EnumType.STRING)
    private QuestionType questionType;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<VariantEntity> variantEntityList;

    @ManyToOne
    @JoinColumn(name="form_id", nullable=false)
    private FormEntity formEntity;



}
