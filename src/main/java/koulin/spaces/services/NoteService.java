package koulin.spaces.services;

import koulin.spaces.entities.Note;
import koulin.spaces.interfaces.ICrud;
import koulin.spaces.interfaces.ISearch;
import koulin.spaces.repository.INoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class NoteService implements ICrud<Note>, ISearch<Note> {

    @Autowired
    private INoteRepository repository;

    public List<Note> getAllNoteByUser(Long id) {
        return repository.findAllNotes(id);
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

    public boolean existNote(Long id){
        var user = repository.findById(id);
        return user.isPresent();
    }

    public List<Note> createSomeNotes(List<Note> note) {
        return repository.saveAll(note);
    }

    @Override
    public Note create(Note note) {
        return repository.save(note);
    }

    @Override
    public Note update(Note note, Long id) {
        return repository.updateNote(note.getId(),
                note.getTitle(), note.getContent(),
                note.getCreationDate(), note.getDateModification(),
                note.isFavorite(), note.getLastScrollPosition());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Note getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<Note> search(String search) {
        return repository.searchNoteByWords(search);
    }
}
