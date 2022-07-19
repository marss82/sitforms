package cz.fel.cvut.sitforms.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class UserFormsFilled {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "form_id")
    private FormEntity form;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    private Boolean filled;

}
