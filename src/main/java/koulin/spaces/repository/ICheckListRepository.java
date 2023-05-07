package koulin.spaces.repository;

import koulin.spaces.entities.CheckList;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICheckListRepository extends CrudRepository<CheckList, Long> {
    @Query(value = "SELECT * FROM CheckList c WHERE c.id_user = ?1", nativeQuery = true)
    public List<CheckList> getAllByIdUser(Long idUser);



}
