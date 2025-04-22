package spring.project.Example.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.User;

import spring.project.Example.Entity.Person;
import spring.project.Example.Entity.Role;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Person, UUID> {

	Person findByEmail(String email);

	
	// Repository method expecting only the id of the Role
	//@Query("SELECT u FROM User u WHERE u.role = :role")
	//List<Person> findByRole(@Param("role") Role role);



	//Optional<Person> findByUsername(String username);
}
