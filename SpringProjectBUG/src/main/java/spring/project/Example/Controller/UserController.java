package spring.project.Example.Controller;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import spring.project.Example.Entity.Person;
import spring.project.Example.Service.JwtService;
import spring.project.Example.Service.UserService;

@RestController
@RequestMapping("/admin/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private JwtService jwtUtil;
    

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") 
    public Person createUser(@RequestBody Person user) {
        return userService.createUser(user);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')") 
    public List<Person> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Person getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Person updateUser(@PathVariable UUID id, @RequestBody Person user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable UUID id) {
        userService.deleteUser(id);
    }
    
}
