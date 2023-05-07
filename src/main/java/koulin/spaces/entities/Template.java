package koulin.spaces.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter @Setter
@EqualsAndHashCode
public class Template {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column()
    private Long id_user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 4000, name = "content")
    private String contentJson;

    @Column(name = "public")
    private Boolean makePublic;

    @Column
    private int likes;

    @Column
    private int shares;

    public Template(){}

    public Template(Long id, Long id_user, String name, String contentJson, Boolean makePublic, int likes, int shares) {
        this.id = id;
        this.id_user = id_user;
        this.name = name;
        this.contentJson = contentJson;
        this.makePublic = makePublic;
        this.likes = likes;
        this.shares = shares;
    }
}
