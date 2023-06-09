package koulin.spaces.repository;

import koulin.spaces.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface INoteRepository extends JpaRepository<Note, Long> {

    @Query(value = "SELECT * FROM Note n WHERE (LOWER(n.title) LIKE %?1% OR LOWER(n.content) LIKE %?1%) AND n.id_user = ?2", nativeQuery = true)
    public List<Note> searchNoteByWords(String search, Long idUser);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Note n SET n.title = ?2, n.content = ?3, n.creation_date = ?4, n.last_modification = ?5, n.favorite = ?6, n.last_position = ?7 WHERE n.id = ?1", nativeQuery = true)
    public int updateNote(Long id, String title, String content, String creationDate, String updateDate, boolean favorite, Double lastPosition);

    @Query(value = "SELECT * FROM Note n WHERE n.id_user = ?1", nativeQuery = true)
    public List<Note> findAllNotesByIdUser(Long id);

    @Query(value = "SELECT * FROM Note n WHERE n.id_folder = ?1", nativeQuery = true)
    public List<Note> findAllNotesByFolderId(Long id_folder);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Note n WHERE n.id_folder = ?1", nativeQuery = true)
    public Integer deleteAllNotesByFolderId(Long id_folder);

}
