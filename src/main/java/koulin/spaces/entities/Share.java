package koulin.spaces.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Entity
@Table
@Getter
@EqualsAndHashCode
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "id_user_receive")
    private Long idUserReceiveIt;

    @Transient
    private List<Long> id_notes;

    @Transient
    private List<Long> id_templates;

    @Column(name = "id_user_send")
    private Long idUserSendIt;

    public Share(Long id, String message, Long idUserReceiveIt, Long idUserSendIt, List<Long> id_notes, List<Long> id_templates) {
        this.id = id;
        this.message = message;
        this.idUserReceiveIt = idUserReceiveIt;
        this.id_notes = id_notes;
        this.idUserSendIt = idUserSendIt;
        this.id_templates = id_templates;
    }

    public Share(){}
}
