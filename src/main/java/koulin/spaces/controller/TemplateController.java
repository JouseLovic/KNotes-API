package koulin.spaces.controller;

import koulin.spaces.entities.Template;
import koulin.spaces.services.StoreService;
import koulin.spaces.services.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/KNote/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    @Autowired
    private StoreService storeService;

    @GetMapping("/{id_user}")
    public ResponseEntity<List<Template>> getAllTemplatesByIdUser(@PathVariable Long id_user){
       List<Template> tempList = templateService.getAllTemplateByIdUser(id_user);
       if(tempList.isEmpty()){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(tempList);
    }

    @PostMapping("/create")
    public ResponseEntity<Template> createTemplate(@RequestBody Template template){
        var temp = templateService.create(template);
        if(temp.getContentJson().isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(temp);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Template> updateTemplate(@RequestBody Template template, @PathVariable Long id){
        var temp = templateService.update(template, id);
        if(temp.getContentJson().isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(temp);

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteTemplate(@PathVariable Long id){
        templateService.delete(id);
        storeService.deleteTemplateFromStore(id);
        return ResponseEntity.ok(true);
    }
}
