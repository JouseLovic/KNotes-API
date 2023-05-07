package koulin.spaces.services;


import koulin.spaces.entities.Issue;
import koulin.spaces.entities.Task;
import koulin.spaces.interfaces.ICrud;
import koulin.spaces.interfaces.ISearch;
import koulin.spaces.repository.IReportIssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ReportIssueService implements ICrud<Issue>,ISearch<Issue> {

    @Autowired
    private IReportIssueRepository repository;

    @Override
    public Issue create(Issue issue) {
        return repository.save(issue);
    }

    @Override
    public Issue update(Issue issue, Long id) {
        Optional<Issue> ref = repository.findById(id);
        if (ref.isPresent()){
            return repository.save(issue);
        }
        return new Issue();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Issue> getAllReports(){
        Iterable<Issue> i = repository.findAll();
        List<Issue> list = new ArrayList<>();
        i.forEach(list::add);
        return list;
    }

    @Override
    public Issue getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public List<Issue> search(String search) {
        return repository.searchIssueByWord(search);
    }
}
