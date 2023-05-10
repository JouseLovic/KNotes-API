package koulin.spaces.services;

import koulin.spaces.entities.Folder;
import koulin.spaces.interfaces.ICrud;
import koulin.spaces.repository.IFolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FolderService implements ICrud<Folder> {

    @Autowired
    private IFolderRepository repository;

    @Override
    public Folder create(Folder folder) {
        return repository.save(folder);
    }

    public List<Folder> getAllFoldersByIdUser(Long id_user){
       return repository.findAllFolderById(id_user);
    }

    @Override
    public Folder update(Folder folder, Long id) {
        Optional<Folder> ref = repository.findById(id);
        if (ref.isPresent())
            return repository.save(folder);
        else
            return folder;
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Folder getById(Long id) {
        return repository.findById(id).orElse(new Folder());
    }
}
