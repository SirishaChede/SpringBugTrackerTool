package spring.project.Example.Entity;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    @CreationTimestamp
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
