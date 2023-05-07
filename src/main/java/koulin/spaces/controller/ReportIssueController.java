package koulin.spaces.controller;

import koulin.spaces.entities.Issue;
import koulin.spaces.services.ReportIssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/issues")
public class ReportIssueController {

    @Autowired
    private ReportIssueService service;

    @GetMapping("/{id}")
    public ResponseEntity<Issue> getById(@PathVariable Long id) {
        Issue issue = service.getById(id);
        if (issue == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(issue);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Issue>> getAllReports() {
        List<Issue> issuesList = service.getAllReports();
        if (issuesList.isEmpty()) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(issuesList);
    }

    @PostMapping("/create")
    public ResponseEntity<Issue> createReport(@RequestBody Issue issue) {
        Issue ref = service.create(issue);
        if (ref == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(ref);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Issue> updateReport(@RequestBody Issue issue, @PathVariable Long id) {
        Issue ref = service.update(issue, id);
        if (ref == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(ref);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteReport(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(true);
    }

    @GetMapping("/search/{text}")
    public ResponseEntity<List<Issue>> searchReport(@PathVariable String search) {
       List<Issue> refList = service.search(search);
       if(refList.isEmpty()){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(refList);
    }
}
