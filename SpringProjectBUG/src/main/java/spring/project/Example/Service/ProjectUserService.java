package spring.project.Example.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.project.Example.Entity.Person;
import spring.project.Example.Entity.Project;
import spring.project.Example.Entity.ProjectUser;
import spring.project.Example.Exception.ResourceNotFoundException;
import spring.project.Example.Repository.ProjectRepository;
import spring.project.Example.Repository.ProjectUserRepository;
import spring.project.Example.Repository.UserRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ProjectUserService {

    @Autowired
    private ProjectUserRepository projectUserRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    // Create a new ProjectUser
    public ProjectUser createProjectUser(ProjectUser projectUser) {
        Optional<Project> projectOptional = projectRepository.findById(projectUser.getProject().getId());
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            projectUser.setProject(project);

            Optional<Person> userOptional = userRepository.findById(projectUser.getUser().getId());
            if (userOptional.isPresent()) {
                Person user = userOptional.get();
                projectUser.setUser(user);

                projectUser.setCreated_on(LocalDateTime.now());
                projectUser.setCreated_by(UUID.randomUUID());

                return projectUserRepository.save(projectUser);
            } else {
                throw new ResourceNotFoundException("User not found with ID: " + projectUser.getUser().getId());
            }
        } else {
            throw new ResourceNotFoundException("Project not found with ID: " + projectUser.getProject().getId());
        }
    }

    // Get a ProjectUser by ID
    public ProjectUser getProjectUserById(int id) {
        return projectUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProjectUser not found with ID: " + id));
    }

    // Get all ProjectUsers
    public List<ProjectUser> getAllProjectUsers() {
        return projectUserRepository.findAll();
    }

    // Update a ProjectUser by ID
    public ProjectUser updateProjectUser(int id, ProjectUser projectUserDetails) {
        ProjectUser existingProjectUser = projectUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProjectUser not found with ID: " + id));

        existingProjectUser.setProject(projectUserDetails.getProject());
        existingProjectUser.setUser(projectUserDetails.getUser());
        existingProjectUser.setIs_active(projectUserDetails.getIs_active());
        existingProjectUser.setModified_on(LocalDateTime.now());
        existingProjectUser.setModified_by(UUID.randomUUID());

        return projectUserRepository.save(existingProjectUser);
    }

    // Delete a ProjectUser by ID
    public void deleteProjectUser(int id) {
        ProjectUser existingProjectUser = projectUserRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ProjectUser not found with ID: " + id));

        projectUserRepository.delete(existingProjectUser);
    }
}
