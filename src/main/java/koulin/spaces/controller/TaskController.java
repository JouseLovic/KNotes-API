package koulin.spaces.controller;

import koulin.spaces.entities.Task;
import koulin.spaces.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/task")
public class TaskController {

    @Autowired
    private TaskService service;

    @GetMapping("/{idUser}")
    public ResponseEntity<List<Task>> getAllTaskByUser(@PathVariable Long idUser){
       List<Task> list = service.getAllTask(idUser);
       if(list.isEmpty()){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(list);
    }

    @PostMapping("/create")
    public ResponseEntity<Task> createTask(@RequestBody Task task){
       Task ref = service.create(task);
       if(ref.getId()<=0){
           return ResponseEntity.internalServerError().build();
       }
       return ResponseEntity.ok(ref);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody Task task, @PathVariable Long id){
        Task ref = service.update(task, id);
        if(ref.getId()<=0){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ref);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteTask(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
