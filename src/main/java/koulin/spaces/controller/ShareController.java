package koulin.spaces.controller;

import koulin.spaces.entities.*;
import koulin.spaces.services.*;
import koulin.spaces.utils.translator.ShareTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/share")
public class ShareController {

    @Autowired
    private ShareService shareService;

    @Autowired
    private UserService userService;

    @Autowired
    private NoteService noteService;

    @Autowired
    private TemplateService templateService;

    @GetMapping("/all-receive/{id}")
    public ResponseEntity<List<ShareTranslator>> getAllSharesReceive(@PathVariable Long id) {
        List<Share> shares = shareService.getAllSharesReceiveByIdUser(id);
        List<ShareTranslator> shareT = new LinkedList<>();
        for (Share share : shares) {
            List<Note> listNotes = noteService.getListFromIdNotes(share.getId_notes());
            List<Template> listTemplates = templateService.getListFromIdTemplate(share.getId_templates());
            User userS = userService.getById(share.getIdUserSendIt());
            User userR = userService.getById(share.getIdUserReceiveIt());
            shareT.add(new ShareTranslator(share.getId(), share.getMessage(), userR, userS, listNotes, listTemplates));
        }
        if (shareT.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(shareT);
    }


    @GetMapping("/all-sent/{id}")
    public ResponseEntity<List<ShareTranslator>> getAllSharesSent(@PathVariable Long id) {
        List<Share> shares = shareService.getAllSharesSendByIdUser(id);
        List<ShareTranslator> shareT = new LinkedList<>();
        for (Share share : shares) {
            List<Note> listNotes = noteService.getListFromIdNotes(share.getId_notes());
            List<Template> listTemplates = templateService.getListFromIdTemplate(share.getId_templates());
            User userS = userService.getById(share.getIdUserSendIt());
            User userR = userService.getById(share.getIdUserReceiveIt());
            shareT.add(new ShareTranslator(share.getId(), share.getMessage(),
                    userR, userS, listNotes,
                    listTemplates));
        }
        if (shareT.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(shareT);
    }

    @PostMapping("/")
    public ResponseEntity<ShareTranslator> shareNoteOrTemplate(@RequestBody Share share) {
        Share shared = shareService.shareNotesOrTemplates(share);
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
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteShare(@PathVariable Long id){
        shareService.deleteShared(id);
        return ResponseEntity.ok(true);
    }

}
