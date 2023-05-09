package koulin.spaces.services;

import koulin.spaces.entities.Share;
import koulin.spaces.repository.IShareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShareService {

    @Autowired
    private IShareRepository repository;

    public Share shareNotesOrTemplates(Share share) {
        return repository.save(share);
    }

    public void deleteShared(Long id) {
        repository.deleteById(id);
    }

    public List<Share> getAllSharesSendByIdUser(Long id) {
        return repository.getAllSharesSendByIdUser(id);
    }

    public List<Share> getAllSharesReceiveByIdUser(Long idUser) {
        return repository.getAllSharesReceiveByIdUser(idUser);
    }


}
