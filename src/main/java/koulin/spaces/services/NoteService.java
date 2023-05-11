package koulin.spaces.services;

import koulin.spaces.entities.Note;
import koulin.spaces.interfaces.ICrud;
import koulin.spaces.interfaces.ISearch;
import koulin.spaces.repository.INoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class NoteService implements ICrud<Note>{

    @Autowired
    private INoteRepository repository;

    public List<Note> getAllNoteByUser(Long id) {
        return repository.findAllNotesByIdUser(id);
    }

    public List<Note> findAllNotesByIdFolder(Long id_folder) {
        List<Note> notes = repository.findAllNotesByFolderId(id_folder);
        if (notes.isEmpty())
            return new LinkedList<Note>();
        return notes;
    }

    public boolean deleteAllNotesPerFolder(Long id_folder) {
        int error = repository.deleteAllNotesByFolderId(id_folder);
        System.out.println(error);
        if (error == 1) {
            return true;
        }
        return false;
    }

    public List<Note> getListFromIdNotes(List<Long> ids) {
        List<Note> auxList = new LinkedList<Note>();
        if (!ids.isEmpty()) {
            for (Long id : ids) {
                Note note = getById(id);
                auxList.add(note);
            }
        }
        return auxList;
    }

    public boolean existNote(Long id) {
        var user = repository.findById(id);
        return user.isPresent();
    }

    public List<Note> createSomeNotes(List<Note> note) {
        return repository.saveAll(note);
    }

    public boolean deleteSomeNOtes(List<Note> notes) {
        for (var nt : notes) {
            delete(nt.getId());
        }
        return true;
    }

    @Override
    public Note create(Note note) {
        return repository.save(note);
    }

    @Override
    public Note update(Note note, Long id) {
        Optional<Note> oldRef = repository.findById(id);
        if (oldRef.isPresent() || !oldRef.isEmpty()) {
            return repository.save(note);
        }
        return new Note();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Note getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public List<Note> search(String search, Long idUser) {
        return repository.searchNoteByWords(search, idUser);
    }
}
