package koulin.spaces;

import koulin.spaces.services.NoteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
public class NoteServiceTest {

    @Autowired
    private NoteService service;

    @Test
    void contextLoads() {
    }

}
