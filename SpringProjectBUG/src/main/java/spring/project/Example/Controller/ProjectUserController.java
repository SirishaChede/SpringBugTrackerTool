package spring.project.Example.Controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.project.Example.Entity.ProjectUser;
import spring.project.Example.Service.ProjectUserService;

@RestController
@RequestMapping("/admin/project-users")
public class ProjectUserController {

    @Autowired
    private ProjectUserService projectUserService;
    
    @PostMapping
    public ProjectUser createProjectUser(@RequestBody ProjectUser projectUser) {
        return projectUserService.createProjectUser(projectUser);
    }

    @GetMapping
    public List<ProjectUser> getAllProjectUsers() {
        return projectUserService.getAllProjectUsers();
    }

    @GetMapping("/{id}")
    public ProjectUser getProjectUserById(@PathVariable int id) {
        return projectUserService.getProjectUserById(id);
    }
    @PutMapping("/{id}")
    public ProjectUser updateProjectUser(@PathVariable int id, @RequestBody ProjectUser projectUser) {
        return projectUserService.updateProjectUser(id, projectUser);
    }

    @DeleteMapping("/{id}")
    public void deleteProjectUser(@PathVariable int id) {
        projectUserService.deleteProjectUser(id);
    }
    
}