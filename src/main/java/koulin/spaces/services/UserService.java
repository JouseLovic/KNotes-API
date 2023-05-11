package koulin.spaces.services;

import koulin.spaces.entities.User;
import koulin.spaces.interfaces.ICrud;
import koulin.spaces.repository.IUserRepository;
import koulin.spaces.utils.translator.Ban;
import koulin.spaces.utils.translator.Desban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements ICrud<User> {

    @Autowired
    private IUserRepository repository;

    @Override
    public User create(User user) {
        return repository.save(user);
    }

    @Override
    public User update(User user, Long id) {
        Optional<User> ref = repository.findById(id);
        if (ref.isPresent()) {
            var userRef = getById(id);
            userRef.setEmail(user.getEmail());
            userRef.setAvatar(user.getAvatar());
            userRef.setTypeUser(user.getTypeUser());
            userRef.setBan(user.getBan());
            userRef.setUsername(user.getUsername());
            userRef.setPass(user.getPass());
            return repository.save(userRef);
        }
        return new User();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<User> getUserByEmail(String email) {
        return repository.getUserByEmail(email);
    }

    public List<User> getUserByUsername(String username) {
        return repository.getUserByUsername(username);
    }

    @Override
    public User getById(Long id) {
        var ref = repository.findById(id);
        return ref.orElseThrow();
    }

    public Boolean existUser(Long id) {
        var user = repository.findById(id);
        return user.isPresent();
    }

    public User banUser(Ban ban) {
        String[] arrayBans = ban.getBan().toArray(new String[0]);
        int error = repository.banUser(ban.getId(), arrayBans, "banned");
        if (error >= 1) {
            return getById(ban.getId());
        }
        return new User();
    }

    public User desBanUser(Desban desBanned) {
        String[] arrayBans = desBanned.getDesban().toArray(new String[0]);
        int error = repository.desBanUser(desBanned.getId(), arrayBans, desBanned.getTypeUser());
        if (error >= 1) {
            return getById(desBanned.getId());
        }
        return new User();
    }
}
