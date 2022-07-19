package cz.fel.cvut.sitforms.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int formsCount;
    @ManyToMany
    @JoinTable(
            name = "category_form",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "form_id"))
    private List<FormEntity> forms;
}
