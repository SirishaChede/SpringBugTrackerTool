package spring.project.Example.Controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@PostMapping("/login")
	public ResponseEntity<String> loginCheck(@RequestBody Person c) {
        // Fetch the user from the database based on the email provided in the request
        Person storedUser = userService.findByEmail(c.getEmail());

       /* if (storedUser == null) {
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
        }

        // Check if the password matches
         if (storedUser.getPassword().equals(c.getPassword())) {
            // Fetch the role ID from the stored user object
            Role roleId = storedUser.getRole();  // Assuming getRoleId() returns the role ID

            // Fetch the role name from the role table based on the role ID
            String roleName = roleservice.getRoleNameById(roleId);

            // Check if the role name is 'ADMIN'
            if ("ADMIN".equalsIgnoreCase(roleName)) {
                // If the user is an admin, generate and return a JWT token*/
     // Assuming 'userService' and 'roleRepository' are already available
        
        if (storedUser != null && storedUser.getRole() != null) {
            // Get the role ID from the user's role
            int roleId = storedUser.getRole().getId();
            
            // Fetch the role by ID (assuming you have a repository for Role)
            Role role = roleservice.getRoleById(roleId);  // Fetch role by ID from the repository

            if (role != null) {
                // Check if the role name is "ADMIN"
                if ("ADMIN".equalsIgnoreCase(role.getName())) {
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
}
	
	/*else {
                // Handle the case where role is not found by ID
                System.out.println("Role not found for the user.");
            }
        } else {
            // Handle the case where the user or role is not found
            System.out.println("User or role not found!");
        }

                /*String jwtToken = jwtUtil.generateToken(c.getEmail());
                return new ResponseEntity<>(jwtToken, HttpStatus.OK);
            } else {
                // If the user is not an admin, return a Forbidden status
                return new ResponseEntity<>("Access Denied: Admins only", HttpStatus.FORBIDDEN);
            }
        }

        return new ResponseEntity<>("Invalid Credentials", HttpStatus.BAD_REQUEST);
    }
	public ResponseEntity<String> login(@RequestBody Person c) {
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