package koulin.spaces.controller;

import koulin.spaces.entities.User;
import koulin.spaces.services.UserService;
import koulin.spaces.utils.translator.Ban;
import koulin.spaces.utils.translator.Desban;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/KNote/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = service.getById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User ref = service.create(user);
        if (ref == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(ref);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        User ref = service.update(user, id);
        if (ref.isFullEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ref);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<List<User>> getUserByEmail(@PathVariable String email) {
        System.out.println("Searching user...");
        List<User> user = service.getUserByEmail(email);
        if (user.isEmpty()) {
            System.out.println("Users not found. Try again with other email");
            return ResponseEntity.notFound().build();
        }
        System.out.println("Found user with id: "+user.get(0).getId());
        return ResponseEntity.ok(user);
    }

    @GetMapping("/username/{name}")
    public ResponseEntity<List<User>> getUserByUsername(@PathVariable String name) {
        System.out.println("Searching user...");
       List<User> user = service.getUserByUsername(name.toLowerCase());
        if (user.isEmpty()) {
            System.out.println("User not found. Try again with other email");
            return ResponseEntity.notFound().build();
        }
        System.out.println("Found user with id: "+user.get(0).getId());
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(true);
    }

    @PutMapping("/ban")
    public ResponseEntity<User> banUser(@RequestBody Ban ban) {
        User user = service.banUser(ban);
        if (user.isFullEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/desban")
    public ResponseEntity<User> desBanUser(@RequestBody Desban desban) {
        User user = service.desBanUser(desban);
        if (user.isFullEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

}
