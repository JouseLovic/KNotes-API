package koulin.spaces.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter @Setter
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user")
    private Long idUser;

    @Column
    private String title;

    @Column
    private String content; //it's a jsonEncode

    @Column(name = "create_date")
    private String creationDate;

    @Column(name = "last_modification")
    private String dateModification;

    @Column
    private boolean favorite;

    @Column(name = "tags")
    private List<String> tag;

    @Column(name = "last_position")
    private Double lastScrollPosition;

    public Note(){}

    public Note(Long id, String title, String content,
                String creationDate, String dateModification,
                boolean favorite, List<String> tag,
                Double lastScrollPosition){
        this.id = id;
        this.title = title;
        this.content = content;
        this.creationDate = creationDate;
        this.dateModification = dateModification;
        this.favorite = favorite;
        this.tag = tag;
        this.lastScrollPosition = lastScrollPosition;
    }
}