package spring.project.Example.Controller;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import spring.project.Example.Entity.Person;
import spring.project.Example.Entity.Role;
import spring.project.Example.Service.JwtService;
import spring.project.Example.Service.RoleService;
import spring.project.Example.Service.UserService;

@RestController
@RequestMapping("/api")
public class loginController {
	@Autowired
	private RoleService roleservice;
	@Autowired
	private UserService userService;
	@Autowired
	private JwtService jwtUtil;
	//@PostMapping("/login")
	/*public ResponseEntity<String> loginCheck(@RequestBody Person c) {
        // Fetch the user from the database based on the email provided in the request
        Person storedUser = userService.findByEmail(c.getEmail());
        
        if (storedUser != null && storedUser.getRole() != null) {
            
            int roleId = storedUser.getRole().getId();
            
            // Fetch the role by ID (assuming you have a repository for Role)
            Role role = roleservice.getRoleById(roleId);  // Fetch role by ID from the repository

            if (role != null) {
                // Check if the role name is "ADMIN"
                if ("ADMIN".equalsIgnoreCase(role.getName())||"Manager".equalsIgnoreCase(role.getName())) {
                	String jwtToken = jwtUtil.generateToken(c.getEmail());
                    return new ResponseEntity<>(jwtToken, HttpStatus.OK);
                } else {
                	 return new ResponseEntity<>("Access Denied: Admins only", HttpStatus.FORBIDDEN);
                    // Continue with your logic for users who do not have the ADMIN role
                }
            } 
        }
        return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
	}
}*/
	@PostMapping("/login")
	public ResponseEntity<String> loginCheck(@RequestBody Person c) {
	    // Fetch the user from the database by email
	    Person storedUser = userService.findByEmail(c.getEmail());

	    if (storedUser != null && storedUser.getRole() != null) {
	        String roleName = storedUser.getRole().getName();

	        // Allow only ADMIN or MANAGER
	        if ("ADMIN".equalsIgnoreCase(roleName) || "MANAGER".equalsIgnoreCase(roleName)) {
	            
	            // Create a UserDetails object with roles
	            List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + roleName.toUpperCase()));
	            User userDetails = new User(
	                storedUser.getEmail(),
	                storedUser.getPassword(), // Optional if you want password checking
	                authorities
	            );
	            
	            if (storedUser.getPassword().equals(c.getPassword())) {
	            // Generate JWT with role in claims
	            String jwtToken = jwtUtil.generateToken(userDetails);

	            return new ResponseEntity<>(jwtToken, HttpStatus.OK);
	        } }else {
	            return new ResponseEntity<>("Access Denied: Only ADMIN or MANAGER allowed", HttpStatus.FORBIDDEN);
	        }
	    }

	    return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
	}
}

	
	
    
    // in the below logic can logged all the users 
	/*public ResponseEntity<String> login(@RequestBody Person c) {
        // Fetch the user from the database based on the email provided in the request
        Person storedUser = userService.findByEmail(c.getEmail());

        if (storedUser == null) {
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
        }

        // Check if the password matches
         if (storedUser.getPassword().equals(c.getPassword())) {
        	 String jwtToken = jwtUtil.generateToken(c.getEmail());
             return new ResponseEntity<>(jwtToken, HttpStatus.OK);
         }
        	
    

    return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
}
}*/