package koulin.spaces.utils.translator;

import koulin.spaces.entities.Note;
import koulin.spaces.entities.Template;
import koulin.spaces.entities.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
public class ShareTranslator {
    private Long id;
    private String message;
    private User userSend;

    private User userReceive;
    private List<Note> listNotes;

    public ShareTranslator(Long id, String message, User userReceive, User userSend, List<Note> listNotes) {
        this.id = id;
        this.message = message;
        this.userSend = userSend;
        this.userReceive = userReceive;
        this.listNotes = listNotes;
    }

    public ShareTranslator(){}

}
