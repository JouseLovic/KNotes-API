package koulin.spaces.services;

import koulin.spaces.entities.Store;
import koulin.spaces.repository.IStoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StoreService {

    @Autowired
    private IStoreRepository repository;

    private Optional<Store> getById(Long id) {
        return repository.findById(id);
    }

    public Store publicTemplate(Store store) {
        Optional<Store> existTemp = getById(store.getId_template());
        if (existTemp.isPresent()) {
            System.out.println("Temp existent" + existTemp.isPresent());
            store.setExistTemplate(true);
            return store;
        }
        return repository.save(store);
    }

    public void deleteTemplateFromStore(Long id_template) {
        repository.deleteById(id_template);
    }

    public List<Store> getAllTemplatesFromStore() {
        return repository.getAllTemplatesForStore();
    }

}
