package koulin.spaces.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
public class CheckList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long id_user;

    @Column(name = "title", nullable = false)
    private String name;

    @Column(nullable = false)
    private String content;

    @Column
    private int updates;

    @Column(name = "creation_date", nullable = false)
    private String creationDate;


    @Column(name = "update_date", nullable = false)
    private String updateDate;

    public CheckList(Long id, Long id_user, String name, String content, int updates, String creationDate, String updateDate) {
        this.id = id;
        this.id_user = id_user;
        this.name = name;
        this.content = content;
        this.updates = updates;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
    }

    public CheckList(){}

}
