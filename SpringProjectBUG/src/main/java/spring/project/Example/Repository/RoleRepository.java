package spring.project.Example.Repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.project.Example.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findById(Role roleId);

	//Role findByEmail(String email);
}
