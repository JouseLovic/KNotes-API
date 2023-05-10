package koulin.spaces.controller;

import koulin.spaces.entities.*;
import koulin.spaces.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/KNote/share")
public class ShareController {

    @Autowired
    private ShareService shareService;

    @Autowired
    private UserService userService;

    @Autowired
    private NoteService noteService;


    @GetMapping("/all-receive/{id}")
    public ResponseEntity<List<Share>> getAllSharesReceive(@PathVariable Long id) {
        List<Share> shares = shareService.getAllSharesReceiveByIdUser(id);
        return ResponseEntity.ok(shares);
        /*
        List<ShareTranslator> shareT = new LinkedList<>();
        for (Share share : shares) {
            List<Note> listNotes = noteService.getListFromIdNotes(share.getId_notes());
            User userS = userService.getById(share.getIdUserSendIt());
            User userR = userService.getById(share.getIdUserReceiveIt());
            shareT.add(new ShareTranslator(share.getId(), share.getMessage(), userR, userS, listNotes));
        }
        if (shareT.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(shareT);
         */
    }


    @GetMapping("/all-send/{id}")
    public ResponseEntity<List<Share>> getAllSharesSent(@PathVariable Long id) {
        List<Share> shares = shareService.getAllSharesSendByIdUser(id);
        return ResponseEntity.ok(shares);
        /*
        List<ShareTranslator> shareT = new LinkedList<>();
        for (Share share : shares) {
            List<Note> listNotes = noteService.getListFromIdNotes(share.getId_notes());
            User userS = userService.getById(share.getIdUserSendIt());
            User userR = userService.getById(share.getIdUserReceiveIt());
            shareT.add(new ShareTranslator(share.getId(), share.getMessage(),
                    userR, userS, listNotes));
        }
        if (shareT.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(shareT);
         */
    }

    @PostMapping("/")
    public ResponseEntity<Share> shareNoteOrTemplate(@RequestBody Share share) {
        Share shared = shareService.shareNotesOrTemplates(share);
        return ResponseEntity.ok(shared);
        /*
        if (shared == null || shared.getMessage() == null) {
            return ResponseEntity.badRequest().build();
        }
        List<Note> listNotes = noteService.getListFromIdNotes(shared.getId_notes());
        List<Template> listTemplates = templateService.getListFromIdTemplate(shared.getId_templates());
        User userS = userService.getById(shared.getIdUserSendIt());
        User userR = userService.getById(shared.getIdUserReceiveIt());
        return ResponseEntity.ok(new ShareTranslator(shared.getId(), shared.getMessage(),
                userR, userS, listNotes,
                listTemplates));
         */
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteShare(@PathVariable Long id){
        shareService.deleteShared(id);
        return ResponseEntity.ok(true);
    }

}
