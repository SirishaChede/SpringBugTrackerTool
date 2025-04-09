package spring.project.Example.Entity;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private Date start_date;
    private Date end_date;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
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
