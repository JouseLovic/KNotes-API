package koulin.spaces.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "Users")
@EqualsAndHashCode
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column(length = 25, nullable = false, name = "email")
    private String email;

    @Column(length = 25, nullable = false)
    private String pass;

    @Column
    private String avatar;

    @Column
    private String typeUser;

    @Column
    private List<String> ban;

    public User(Long id, String username, String email, String pass, String avatar, String typeUser, List<String> typeBan) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.pass = pass;
        this.avatar = avatar;
        this.typeUser = typeUser;
        this.ban = typeBan;
    }

    public User() {
    }
}
