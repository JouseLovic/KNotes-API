package koulin.spaces.controller;

import koulin.spaces.DTO.FolderDTO;
import koulin.spaces.entities.Folder;
import koulin.spaces.entities.Note;
import koulin.spaces.services.FolderService;
import koulin.spaces.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/KNote/folder")
public class FolderController {

    @Autowired
    private FolderService folderService;

    @Autowired
    private NoteService noteService;

    @GetMapping("/All/{id_user}")
    public ResponseEntity<List<FolderDTO>> getAllFoldersByUser(@PathVariable Long id_user) {
        List<Folder> refList = folderService.getAllFoldersByIdUser(id_user);
        List<FolderDTO> DTOs = new LinkedList<>();
        if (refList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        for (Folder folder : refList) {
            List<Note> notes = noteService.findAllNotesByIdFolder(folder.getId());
            FolderDTO folderDTO = new FolderDTO(folder.getId(), folder.getId_user(),
                    folder.getName(), folder.getDescription(),
                    folder.getCreationDate(), folder.isPinned(),
                    folder.getPassword(), notes);
            DTOs.add(folderDTO);
        }
        return ResponseEntity.ok(DTOs);
    }

    @PostMapping("/create")
    public ResponseEntity<FolderDTO> createFolder(@RequestBody Folder folder) {
        Folder ref = folderService.create(folder);
        if (ref.isFullEmpty()) {
            System.out.println("Something out wrong...");
            return ResponseEntity.badRequest().build();
        }
        FolderDTO folderDTO = new FolderDTO(ref.getId(), ref.getId_user(),
                ref.getName(), ref.getDescription(), ref.getCreationDate(),
                ref.isPinned(), ref.getPassword(), new LinkedList<>());
        return ResponseEntity.ok(folderDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<FolderDTO> updateFolderById(@RequestBody Folder folder, @PathVariable Long id) {
        Folder ref = folderService.update(folder, id);
        if (ref.isFullEmpty()) {
            System.out.println("Folder not find...");
            return ResponseEntity.notFound().build();
        }
        List<Note> notes = noteService.findAllNotesByIdFolder(ref.getId());
        FolderDTO folderDTO = new FolderDTO(ref.getId(), ref.getId_user(),
                ref.getName(), ref.getDescription(), ref.getCreationDate(),
                ref.isPinned(), ref.getPassword(), notes);
        return ResponseEntity.ok(folderDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteFolderById(@PathVariable Long id) {
        folderService.delete(id);
        boolean hasError = noteService.deleteAllNotesPerFolder(id);
        return hasError ? ResponseEntity.badRequest().build() : ResponseEntity.ok(true);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FolderDTO> getFolderById(@PathVariable Long id) {
        Folder ref = folderService.getById(id);
        if (ref.isFullEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Note> notes = noteService.findAllNotesByIdFolder(ref.getId());
        FolderDTO folderDTO = new FolderDTO(ref.getId(), ref.getId_user(),
                ref.getName(), ref.getDescription(),
                ref.getCreationDate(), ref.isPinned(),
                ref.getPassword(), notes);
        return ResponseEntity.ok(folderDTO);
    }
}


