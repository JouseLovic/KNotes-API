package koulin.spaces.repository;

import koulin.spaces.entities.Folder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFolderRepository extends CrudRepository<Folder, Long> {
}
