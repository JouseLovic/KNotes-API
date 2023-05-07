package koulin.spaces.repository;

import koulin.spaces.entities.Template;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ITemplateRepository extends JpaRepository<Template, Long> {
    @Query(value = "SELECT * FROM Template s WHERE s.name like %?1%", nativeQuery = true)
    public List<Template> searchNoteByWords(String search);

    @Query(value = "SELECT * FROM Template s WHERE s.id_user = ?1", nativeQuery = true)
    public List<Template> getAllTemplateByIdUser(Long id);



}
