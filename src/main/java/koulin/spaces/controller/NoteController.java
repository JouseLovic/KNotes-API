package koulin.spaces.controller;

import koulin.spaces.entities.Note;
import koulin.spaces.services.NoteService;
import koulin.spaces.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/KNote/note")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private UserService userService;

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Note>> getAllNotesByUser(@PathVariable Long id) {
        boolean result = userService.existUser(id);
        if (!result) {
            System.out.println("User not found. Please, try again with other id");
            return ResponseEntity.notFound().build();
        }
        List<Note> notes = noteService.getAllNoteByUser(id);
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/search/{text}/{id_user}")
    public ResponseEntity<List<Note>> searchNote(@PathVariable String text, @PathVariable Long id_user) {
        List<Note> listNotes = noteService.search(text.toLowerCase(), id_user);
        if (listNotes.isEmpty()) {
            System.out.println("Note not found. Please try again with the different text that: " + text);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listNotes);
    }

    @PostMapping("/create")
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        Note refN = noteService.create(note);
        if (refN.isFullEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(refN);
    }

    @PostMapping("/create-several-note")
    public ResponseEntity<List<Note>> createSeveralNotes(@RequestBody List<Note> note) {
        List<Note> listNotes = noteService.createSomeNotes(note);
        if (listNotes.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(listNotes);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Note> updateNote(@RequestBody Note note, @PathVariable Long id) {
        Note ref = noteService.update(note, id);
        if (ref.isFullEmpty()) {
            System.out.println("A error has ocurrerd. Please, try again");
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ref);
    }

   /* @DeleteMapping("/delete-some-notes")
    public ResponseEntity<Boolean> deleteSomeNotes(@RequestBody List<Note> note){
       final boolean hasError = noteService.deleteSomeNOtes(note);
       return ResponseEntity.ok(hasError);
    } */

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteNote(@PathVariable Long id) {
        noteService.delete(id);
        return ResponseEntity.ok(true);
    }

}
