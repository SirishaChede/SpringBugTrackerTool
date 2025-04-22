package spring.project.Example.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.project.Example.Entity.Role;
import spring.project.Example.Exception.ResourceNotFoundException;
import spring.project.Example.Entity.Person;
import spring.project.Example.Repository.RoleRepository;
import spring.project.Example.Repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoleService   {

    @Autowired
    private RoleRepository roleRepository;
   

    public Role createRole(Role role) {
    	role.setCreated_on(LocalDateTime.now());
        role.setCreated_by(UUID.randomUUID());
        return roleRepository.save(role);
    }
    
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(int id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.orElseThrow(() -> new RuntimeException("Role not found"));
    }
    

    public Role updateRole(int id, Role roleDetails) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        if (roleOptional.isPresent()) {
            Role role = roleOptional.get();
            role.setName(roleDetails.getName());
            role.setModified_on(LocalDateTime.now());
            role.setModified_by(UUID.randomUUID()); 
            return roleRepository.save(role);
        }else {
        	throw new ResourceNotFoundException("Role not found");
        }
    }

    public void deleteRole(int id) {
        Role role = getRoleById(id);
        roleRepository.delete(role);
    }

    public String getRoleNameById(Role roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        String roleName="null";  // Default to "UNKNOWN"

        if (role.isPresent()) {
              roleName = role.get().getName();
        }
		return roleName;
	}
}
   

	
