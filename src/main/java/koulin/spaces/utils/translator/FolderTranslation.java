package koulin.spaces.utils.translator;

import koulin.spaces.entities.Note;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class FolderTranslation {
    private Long id;

    private Long id_user;
    private String name;
    private String description;
    private String createDate;
    private boolean pinned;

    private String password;

    private List<Note> notes;

    public FolderTranslation(Long id, Long id_user, String name, String description, String createDate, boolean pinned, String password, List<Note> notes) {
        this.id = id;
        this.id_user = id_user;
        this.name = name;
        this.description = description;
        this.createDate = createDate;
        this.pinned = pinned;
        this.password = password;
        this.notes = notes;
    }
}
