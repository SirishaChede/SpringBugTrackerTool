package spring.project.Example.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.project.Example.Entity.Person;
import spring.project.Example.Entity.Role;
import spring.project.Example.Exception.ResourceNotFoundException;
import spring.project.Example.Repository.RoleRepository;
import spring.project.Example.Repository.UserRepository;

import java.util.UUID;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public Person createUser(Person user) {
        Optional<Role> roleOptional = roleRepository.findById(user.getRole().getId());
        if (roleOptional.isPresent()) {
            Role role = roleOptional.get();
            user.setRole(role);  // Assigning the role to the user
            user.setCreated_on(LocalDateTime.now());
            user.setCreated_by(UUID.randomUUID());  // Set the admin user who creates the user
            return userRepository.save(user);
        } else {
            throw new ResourceNotFoundException("Role not found");
        }
    }

    public List<Person> getAllUsers() {
        return userRepository.findAll();
    }

    public Person getUserById(UUID id) {
        Optional<Person> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Person updateUser(UUID id, Person userDetails) {
        Optional<Person> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            Person user = userOptional.get();
            Optional<Role> roleOptional = roleRepository.findById(userDetails.getRole().getId());
            if (roleOptional.isPresent()) {
                user.setFname(userDetails.getFname());
                user.setLname(userDetails.getLname());
                user.setEmail(userDetails.getEmail());
                user.setPassword(userDetails.getPassword());
                user.setIs_active(userDetails.getIs_active());
                user.setRole(roleOptional.get());  // Reassign the role
                user.setModified_on(LocalDateTime.now());
                user.setModified_by(UUID.randomUUID());  // Set the admin user who modifies the user
                return userRepository.save(user);
            } else {
                throw new ResourceNotFoundException("Role not found");
            }
        }
        return null;
    }
    public boolean deleteUser(UUID id) {
        Optional<Person> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            userRepository.delete(userOptional.get());
            return true;
        }
        return false;
    }

    @Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Person c=userRepository.findByEmail(email);
		return new User(c.getEmail(),c.getPassword(),Collections.emptyList());
	}

	public Person findByEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}
	 

	


    
}
