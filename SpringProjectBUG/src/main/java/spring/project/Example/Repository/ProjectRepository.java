package spring.project.Example.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import spring.project.Example.Entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
