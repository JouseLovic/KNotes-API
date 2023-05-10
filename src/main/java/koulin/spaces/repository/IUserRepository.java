package koulin.spaces.repository;

import koulin.spaces.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Query(value = "UPDATE Users u set u.type_user = ?3, u.ban = ?2 WHERE u.id = ?1", nativeQuery = true)
    public User banUser(Long id, List<String> ban, String type);

    @Modifying
    @Query(value = "UPDATE Users u set u.type_user = ?3, u.ban = ?2 WHERE u.id = ?1", nativeQuery = true)
    public User desBanUser(Long id, List<String> ban, String type);

    @Query(value = "SELECT * FROM User u WHERE u.email = ?1", nativeQuery = true)
    public User getUserByEmail(String email);

    @Query(value = "SELECT * FROM User u WHERE u.username = ?1", nativeQuery = true)
    public User getUserByUsername(String username);


}
