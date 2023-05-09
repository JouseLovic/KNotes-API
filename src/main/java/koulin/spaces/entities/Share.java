package koulin.spaces.entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@EqualsAndHashCode
public class Share {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "id_user_receive")
    private Long idUserReceiveIt;
    @Column(name = "id_user_send")
    private Long idUserSendIt;

    @Column
    private List<Long> id_notes;

    public Share(Long id, String message, Long idUserReceiveIt, Long idUserSendIt, List<Long> id_notes) {
        this.id = id;
        this.message = message;
        this.idUserReceiveIt = idUserReceiveIt;
        this.id_notes = id_notes;
        this.idUserSendIt = idUserSendIt;
    }

    public Share() {
    }
}
