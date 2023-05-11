package koulin.spaces.repository;

import jakarta.transaction.Transactional;
import koulin.spaces.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE Users SET type_user = ?3, ban = ?2 WHERE id = ?1", nativeQuery = true)
    public int banUser(Long id, String[] ban, String type);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Users SET type_user = ?3, ban = ?2 WHERE id = ?1", nativeQuery = true)
    public int desBanUser(Long id, String[] ban, String type);

    @Query(value = "SELECT * FROM Users u WHERE UPPER (u.email) LIKE %?1% or LOWER(u.email) LIKE %?1%", nativeQuery = true)
    public List<User> getUserByEmail(String email);

    @Query(value = "SELECT * FROM Users u WHERE UPPER (u.username) LIKE %?1% or LOWER(u.username) LIKE %?1%", nativeQuery = true)
    public List<User> getUserByUsername(String username);


}
