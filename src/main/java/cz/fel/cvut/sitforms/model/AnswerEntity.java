package cz.fel.cvut.sitforms.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Data
public class AnswerEntity {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "variant_id")
    private VariantEntity variant;
}
