package spring.project.Example.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import spring.project.Example.Entity.ProjectUser;

public interface ProjectUserRepository extends JpaRepository<ProjectUser, Integer> {
}
