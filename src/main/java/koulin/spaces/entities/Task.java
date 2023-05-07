package koulin.spaces.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter @Setter @EqualsAndHashCode
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long id_user;

    @Column(nullable = false, name = "detail")
    private String details;

    @Column(nullable = false)
    private String finishItDate;

    @Column(nullable = false)
    private String creationDate;

    public Task(Long id, Long id_user, String details, String finishItDate, String creationDate) {
        this.id = id;
        this.id_user = id_user;
        this.details = details;
        this.finishItDate = finishItDate;
        this.creationDate = creationDate;
    }

    public Task(){}

}
