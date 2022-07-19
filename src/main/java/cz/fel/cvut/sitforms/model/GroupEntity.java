package cz.fel.cvut.sitforms.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class GroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    private UserEntity author;

    @ManyToMany(mappedBy = "assignedGroups", cascade = CascadeType.MERGE)
    private List<UserEntity> userEntityList = new ArrayList<>();

}
