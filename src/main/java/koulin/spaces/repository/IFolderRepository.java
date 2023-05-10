package koulin.spaces.repository;

import koulin.spaces.entities.Folder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFolderRepository extends CrudRepository<Folder, Long> {

    @Query(value = "SELECT * FROM Folder f WHERE f.id_user = ?1", nativeQuery = true)
    public List<Folder> findAllFolderById(Long id);


}
