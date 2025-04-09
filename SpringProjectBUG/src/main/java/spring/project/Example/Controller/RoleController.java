package spring.project.Example.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import spring.project.Example.Entity.Role;
import spring.project.Example.Service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/admin/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
   // @PreAuthorize("hasRole('ADMIN')") 
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')") 
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public Role getRoleById(@PathVariable int id) {
        return roleService.getRoleById(id);
    }
    
    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public Role updateRole(@PathVariable int id, @RequestBody Role role) {
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public void deleteRole(@PathVariable int id) {
        roleService.deleteRole(id);
    }
}