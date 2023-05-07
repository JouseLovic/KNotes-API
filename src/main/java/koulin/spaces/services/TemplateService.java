package koulin.spaces.services;

import koulin.spaces.entities.Note;
import koulin.spaces.entities.Template;
import koulin.spaces.interfaces.ICrud;
import koulin.spaces.interfaces.ISearch;
import koulin.spaces.repository.ITemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TemplateService implements ICrud<Template>, ISearch<Template> {

    @Autowired
    private ITemplateRepository repository;

    public List<Template> getAllTemplates() {
        return repository.findAll();
    }

    @Override
    public Template create(Template template) {
        return repository.save(template);
    }

    @Override
    public Template update(Template template, Long id) {
        Template ref = repository.findById(id).orElseThrow();
        ref.setContentJson(template.getContentJson());
        ref.setName(template.getName());
        ref.setLikes(template.getLikes());
        ref.setShares(template.getShares());
        ref.setMakePublic(template.getMakePublic());
        return repository.save(ref);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Template getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Boolean existTemplate(Long id){
        var temp = repository.findById(id);
        return temp.isPresent();
    }

    public List<Template> getListFromIdTemplate(List<Long> ids) {
        List<Template> auxList = new LinkedList<>();
        if (!ids.isEmpty()) {
            for (Long id : ids) {
                Template temp = getById(id);
                auxList.add(temp);
            }

        }
        return auxList;
    }


    public List<Template> getAllTemplateByIdUser(Long idUser) {
        return repository.getAllTemplateByIdUser(idUser);
    }

    @Override
    public List<Template> search(String search) {
        return repository.searchNoteByWords(search);
    }
}
