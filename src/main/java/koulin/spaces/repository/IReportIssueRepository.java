package koulin.spaces.repository;

import koulin.spaces.entities.Issue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface IReportIssueRepository extends CrudRepository<Issue, Long> {
    @Query(value = "SELECT * FROM Issue i WHERE i.description like %?1% or i.reason like %?1% or i.id %?1%, or i.id_user like %?1%", nativeQuery = true)
    public List<Issue> searchIssueByWord(String search);

}
