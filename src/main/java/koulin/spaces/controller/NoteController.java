package koulin.spaces.controller;

import koulin.spaces.entities.Note;
import koulin.spaces.services.NoteService;
import koulin.spaces.services.UserService;
import koulin.spaces.utils.translator.NoteRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/KNote/note")
public class NoteController {

    private NoteService noteService;
    private UserService userService;

    @GetMapping("/user")
    public ResponseEntity<List<Note>> getAllNotesByUser(@RequestBody NoteRequest request) {
        boolean result = userService.existUser(request.getId());
        if (!result) {
            System.out.println("User not found. Please, try again with other id");
            return ResponseEntity.notFound().build();
        }
        List<Note> notes = noteService.getAllNoteByUser(request.getId());
        return ResponseEntity.ok(notes);
    }

    @GetMapping("/search/{text}")
    public ResponseEntity<List<Note>> searchNote(@PathVariable String text){
        List<Note> listNotes = noteService.search(text);
        if(listNotes.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(listNotes);
    }

    @PostMapping("/create")
    public ResponseEntity<Note> createNote(@RequestBody Note note){
        Note refN = noteService.create(note);
        if(refN.getContent()==null || refN==null){
           return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(refN);
    }

    @PostMapping("/create-several-note")
    public ResponseEntity<List<Note>> createSeveralNotes(@RequestBody List<Note> note){
       List<Note> listNotes = noteService.createSomeNotes(note);
       if(listNotes.isEmpty()){
           return ResponseEntity.badRequest().build();
       }
       return ResponseEntity.ok(listNotes);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Note> updateNote(@RequestBody Note note, @PathVariable Long id){
        Note ref = noteService.update(note, id);
        if(ref.getContent()==null || ref==null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(ref);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteNote(@PathVariable Long id){
        noteService.delete(id);
        return ResponseEntity.ok(true);
    }

}
