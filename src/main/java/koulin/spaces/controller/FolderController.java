package koulin.spaces.controller;

import koulin.spaces.services.FolderService;
import koulin.spaces.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/KNote/folder")
public class FolderController {

    @Autowired
    private FolderService folderService;

    @Autowired
    private NoteService noteService;

}
