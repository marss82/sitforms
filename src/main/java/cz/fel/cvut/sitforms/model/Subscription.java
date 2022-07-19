package cz.fel.cvut.sitforms.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Accessors(chain = true)
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate creationDate;
    private LocalDate dueDate;
    @Enumerated(EnumType.STRING)
    private SubscriptionType subscriptionType;
    private int formCreationCount = 0;
    private int groupCreationCount = 0;
    private int assigningPeopleLimitCount = 10;
    @OneToOne(cascade = CascadeType.ALL)
    private UserEntity userEntity;
}
