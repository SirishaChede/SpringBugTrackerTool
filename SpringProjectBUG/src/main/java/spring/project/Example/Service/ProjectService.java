package spring.project.Example.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.project.Example.Entity.Project;
import spring.project.Example.Exception.ResourceNotFoundException;
import spring.project.Example.Repository.ProjectRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project CreateProject(Project project) {
		project.setCreated_on(LocalDateTime.now());
		project.setCreated_by(UUID.randomUUID());
		return projectRepository.save(project);
		
	}

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(int id) {
        Optional<Project> project = projectRepository.findById(id);
        return project.orElseThrow(() -> new RuntimeException("Project not found"));
    }

    public Project Updateproject(int id,Project project) {
		Optional<Project> projectoptional=projectRepository.findById(id);
		if(projectoptional.isPresent()) {
			Project project1=projectoptional.get();
			project1.setTitle(project.getTitle());
			project1.setDescription(project.getDescription());
			project1.setStart_date(project.getStart_date());
			project1.setEnd_date(project.getEnd_date());
			project1.setModified_on(LocalDateTime.now());
			project1.setModified_by(UUID.randomUUID());
			return projectRepository.save(project);
			
		}else {
			throw  new ResourceNotFoundException("Project not found"+id);
		}
    }

    public boolean DeleteProject(int id) {
		Optional<Project> project= projectRepository.findById(id);
		if(project.isPresent()) {
			 projectRepository.delete(project.get());
			 return true;
		}
		else {
			throw new ResourceNotFoundException("project not Found"+id);
		}
	}
}
