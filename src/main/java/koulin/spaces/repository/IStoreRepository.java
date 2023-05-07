package koulin.spaces.repository;

import koulin.spaces.entities.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStoreRepository extends JpaRepository<Store, Long> {
    @Query(value = "SELECT * FROM Store", nativeQuery = true)
    public List<Store> getAllTemplatesForStore();
}
