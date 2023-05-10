package koulin.spaces.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long id_user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true, length = 200)
    private String description;

    @Column(nullable = false)
    private boolean pinned;

    @Column(nullable = true, length = 8)
    private String password;

    @Column(nullable = false, name = "creation_date")
    private String creationDate;

    public Folder(Long id, Long id_user, String name, String description, boolean pinned, String password, String creationDate) {
        this.id = id;
        this.id_user = id_user;
        this.name = name;
        this.description = description;
        this.pinned = pinned;
        this.password = password;
        this.creationDate = creationDate;
    }

    public Folder(){}

    public boolean isFullEmpty(){
        return id == null || id <= 0 || name == null || creationDate.equals("") || creationDate.isEmpty() || id_user == null || id_user <= 0;
    }
}
