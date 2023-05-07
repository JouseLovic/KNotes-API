package koulin.spaces.services;

import koulin.spaces.entities.User;
import koulin.spaces.interfaces.ICrud;
import koulin.spaces.repository.IUserRepository;
import koulin.spaces.utils.translator.Ban;
import koulin.spaces.utils.translator.Desban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        User ref = repository.findById(id).orElseThrow();
        ref.setUsername(user.getUsername());
        ref.setTypeUser(user.getTypeUser());
        ref.setEmail(user.getEmail());
        ref.setBan(user.getBan());
        ref.setAvatar(user.getAvatar());
        ref.setPass(user.getPass());
        return repository.save(ref);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public User getUserByEmail(String email){
        return repository.getUserByEmail(email);
    }

    public User getUserByUsername(String username){
        return repository.getUserByUsername(username);
    }

    @Override
    public User getById(Long id) {
        var ref = repository.findById(id);
        return ref.orElseThrow();
    }

    public Boolean existUser(Long id){
        var user = repository.findById(id);
        return user.isPresent();
    }

    public User banUser(Ban ban) {
        return repository.banUser(ban.getId(), ban.getBan(), "banned");
    }

    public User desBanUser(Desban desBanned) {
        return repository.desBanUser(desBanned.getId(), desBanned.getDesban(), desBanned.getTypeUser());
    }
}
