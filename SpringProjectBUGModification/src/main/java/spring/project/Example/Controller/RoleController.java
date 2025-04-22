package spring.project.Example.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import spring.project.Example.Entity.Role;
import spring.project.Example.Service.JwtService;
import spring.project.Example.Service.RoleService;

import java.util.List;

/*@RestController
@RequestMapping("/admin/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }
 

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable int id) {
        return roleService.getRoleById(id);
    }
    
    @PutMapping("/{id}")
    public Role updateRole(@PathVariable int id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/{id}")
    public void deleteRole(@PathVariable int id) {
        roleService.deleteRole(id);
    }
}*/
@RestController
@RequestMapping("/admin/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private JwtService jwtService;

    
    @PostMapping
    public ResponseEntity<?> createRole(@RequestHeader("Authorization") String authHeader,
                                        @RequestBody Role role) {
        if (hasAdminAccess(authHeader)) {
            return ResponseEntity.ok(roleService.createRole(role));
        } else {
            return new ResponseEntity<>("Access Denied: Admins only", HttpStatus.FORBIDDEN);
        }
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRole(@RequestHeader("Authorization") String authHeader,
                                        @PathVariable int id,
                                        @RequestBody Role role) {
        if (hasAdminAccess(authHeader)) {
            return ResponseEntity.ok(roleService.updateRole(id, role));
        } else {
            return new ResponseEntity<>("Access Denied: Admins only", HttpStatus.FORBIDDEN);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRole(@RequestHeader("Authorization") String authHeader,
                                        @PathVariable int id) {
        if (hasAdminAccess(authHeader)) {
        	roleService.deleteRole(id);
            return ResponseEntity.ok("Role deleted");
        } else {
            return new ResponseEntity<>("Access Denied: Admins only", HttpStatus.FORBIDDEN);
        }
    }

    
    @GetMapping
    public ResponseEntity<?> getAllRoles(@RequestHeader("Authorization") String authHeader) {
        if (hasAdminOrManagerAccess(authHeader)) {
            return ResponseEntity.ok(roleService.getAllRoles());
        } else {
            return new ResponseEntity<>("Access Denied", HttpStatus.FORBIDDEN);
        }
    }
    private boolean hasAdminAccess(String authHeader) {
        String token = extractToken(authHeader);
        List<String> roles = jwtService.extractRoles(token);
        return roles.contains("ROLE_ADMIN");
    }

    private boolean hasAdminOrManagerAccess(String authHeader) {
        String token = extractToken(authHeader);
        List<String> roles = jwtService.extractRoles(token);
        return roles.contains("ROLE_ADMIN") || roles.contains("ROLE_MANAGER");
    }

    private String extractToken(String authHeader) {
        return authHeader != null && authHeader.startsWith("Bearer ")
                ? authHeader.substring(7)
                : authHeader;
    }
}
