package spring.project.Example.Entity;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;



@Entity
public class Person {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    //@GenericGenerator(name = "UUID", strategy = "uuid2")
    private UUID id;

    private String fname;
    private String lname;
    private String email;
    private String password;
    @ManyToOne
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    private Boolean is_active;
    private LocalDateTime created_on;
    private UUID created_by;
    private LocalDateTime modified_on;
    private UUID modified_by;
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	

    // Getters and setters
    
}
