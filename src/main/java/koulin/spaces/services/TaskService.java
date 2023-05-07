package koulin.spaces.services;

import koulin.spaces.entities.Task;
import koulin.spaces.interfaces.ICrud;
import koulin.spaces.interfaces.ISearch;
import koulin.spaces.repository.ITaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class TaskService implements ICrud<Task>{

    @Autowired
    private ITaskRepository repository;

    public List<Task> getAllTask(Long idUser){
        return repository.getAllTaskByIdUser(idUser);
    }

    @Override
    public Task create(Task task) {
        return repository.save(task);
    }

    public List<Task> createSeveralTask(List<Task> task){
        Iterable<Task> i = repository.saveAll(task);
        List<Task> savedTasks = new ArrayList<>();
        i.forEach(savedTasks::add);
        return savedTasks;
    }

    @Override
    public Task update(Task task, Long id) {
        Task ref = repository.findById(id).orElseThrow();
        ref.setDetails(task.getDetails());
        ref.setFinishItDate(task.getFinishItDate());
        return repository.save(ref);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Task getById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
