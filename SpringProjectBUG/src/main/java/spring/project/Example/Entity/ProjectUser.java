package spring.project.Example.Entity;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class ProjectUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "project_id", referencedColumnName = "id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private Person user;

    private Boolean is_active;
    private LocalDateTime created_on;
    private UUID created_by;
    private LocalDateTime modified_on;
    private UUID modified_by;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
	}
	public Person getUser() {
		return user;
	}
	public void setUser(Person user) {
		this.user = user;
	}
	public Boolean getIs_active() {
		return is_active;
	}
	public void setIs_active(Boolean is_active) {
		this.is_active = is_active;
	}
	public LocalDateTime getCreated_on() {
		return created_on;
	}
	public void setCreated_on(LocalDateTime created_on) {
		this.created_on = created_on;
	}
	public UUID getCreated_by() {
		return created_by;
	}
	public void setCreated_by(UUID created_by) {
		this.created_by = created_by;
	}
	public LocalDateTime getModified_on() {
		return modified_on;
	}
	public void setModified_on(LocalDateTime modified_on) {
		this.modified_on = modified_on;
	}
	public UUID getModified_by() {
		return modified_by;
	}
	public void setModified_by(UUID modified_by) {
		this.modified_by = modified_by;
	}

    // Getters and setters
}
