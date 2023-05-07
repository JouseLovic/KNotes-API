package koulin.spaces.repository;

import koulin.spaces.entities.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IShareRepository extends JpaRepository<Share, Long> {

    @Query(value = "SELECT * FROM Share s WHERE s.id_user_send = ?1", nativeQuery = true)
    public List<Share> getAllSharesSendByIdUser(Long id);

    @Query(value = "SELECT * FROM Share s WHERE s.id_user_receive = ?1", nativeQuery = true)
    public List<Share> getAllSharesReceiveByIdUser(Long id);

    @Query(value = "INSERT INTO Share (message, id_user_receive, id_user_send, id_notes, id_templates) VALUES (?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    public Share shareNotes(String message, Long id_user_r, Long id_user_s, List<Long> id_notes, List<Long> id_templates);

}
