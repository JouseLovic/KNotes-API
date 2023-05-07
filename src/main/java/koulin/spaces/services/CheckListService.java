package koulin.spaces.services;

import koulin.spaces.entities.CheckList;
import koulin.spaces.interfaces.ICrud;
import koulin.spaces.repository.ICheckListRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CheckListService implements ICrud<CheckList> {

    private ICheckListRepository repository;

    public List<CheckList> getAllByIdUser(Long idUser){
        return repository.getAllByIdUser(idUser);
    }

    @Override
    public CheckList create(CheckList checkList) {
        return repository.save(checkList);
    }

    @Override
    public CheckList update(CheckList checkList, Long id) {
        Optional<CheckList> ref = repository.findById(id);
        if(ref.isPresent()){
            return repository.save(checkList);
        }
        return new CheckList();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public CheckList getById(Long id) {
        return repository.findById(id).orElseThrow();
    }
}
