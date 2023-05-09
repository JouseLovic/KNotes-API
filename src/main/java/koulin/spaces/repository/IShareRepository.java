package koulin.spaces.repository;

import koulin.spaces.entities.Share;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface IShareRepository extends JpaRepository<Share, Long> {

    @Query(value = "SELECT * FROM Share s WHERE s.id_user_send = ?1", nativeQuery = true)
    public List<Share> getAllSharesSendByIdUser(Long id);

    @Query(value = "SELECT * FROM Share s WHERE s.id_user_receive = ?1", nativeQuery = true)
    public List<Share> getAllSharesReceiveByIdUser(Long id);
}
