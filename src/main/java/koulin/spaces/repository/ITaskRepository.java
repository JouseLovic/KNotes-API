package koulin.spaces.repository;

import koulin.spaces.entities.Task;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITaskRepository extends CrudRepository<Task, Long> {

    @Query(value = "SELECT * FROM Task t WHERE t.id_user = ?1", nativeQuery = true)
    public List<Task> getAllTaskByIdUser(Long id);

}
