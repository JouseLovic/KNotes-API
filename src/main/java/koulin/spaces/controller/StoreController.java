package koulin.spaces.controller;

import koulin.spaces.entities.Store;
import koulin.spaces.entities.Template;
import koulin.spaces.services.StoreService;
import koulin.spaces.services.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping(value = "/KNote/store")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private TemplateService templateService;


    @GetMapping("/templates")
    public ResponseEntity<List<Template>> getAllTemplatesFromStore() {
        List<Store> list = storeService.getAllTemplatesFromStore();
        List<Template> result = new LinkedList<>();
        for (Store store : list) {
            Template temp = templateService.getById(store.getId_template());
            result.add(temp);
        }
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping("/public/{id_template}")
    public ResponseEntity<Template> publicTemplate(@PathVariable Long id_template) {
        if (templateService.existTemplate(id_template)) {
            Store store = storeService.publicTemplate(new Store(id_template));
            Template temp = templateService.getById(store.getId_template());
            if (!store.isExistTemplate()) {
                temp.setMakePublic(true);
                templateService.update(temp, temp.getId());
            } else {
                return ResponseEntity.badRequest().build();
            }
            return ResponseEntity.ok(temp);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteTemplateFromStore(@PathVariable Long id) {
        storeService.deleteTemplateFromStore(id);
        Template temp = templateService.getById(id);
        temp.setMakePublic(false);
        templateService.update(temp, temp.getId());
        return ResponseEntity.ok(true);
    }

    @GetMapping("/search/{text}")
    public ResponseEntity<List<Template>> searchTemplateFromStore(@PathVariable String text) {
        List<Template> temp = templateService.search(text);
        if (temp.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(temp);
    }

}
