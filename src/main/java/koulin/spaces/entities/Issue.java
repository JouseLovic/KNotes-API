package koulin.spaces.entities;

import jakarta.persistence.*;
import koulin.spaces.utils.enums.TypeReason;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long id_user;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false, name = "image")
    private String refImage;

    @Column
    private String description;


    public Issue(Long id, String reason, String image, String description, Long id_user) {
        this.id = id;
        this.reason = reason;
        this.refImage = image;
        this.description = description;
        this.id_user = id_user;
    }

    public Issue() {
    }

}
