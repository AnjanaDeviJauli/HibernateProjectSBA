package sba.sms.models;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString.Exclude;
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "student")
public class Student {
	@Id
	@NonNull
	@Column(length = 50, name = "email")
	String email;
	@NonNull
	@Column(length = 50, name = "name")
	String name;
	@NonNull
	@Column(length = 50, name = "password")
	String password;
    
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE,CascadeType.DETACH},
			fetch = FetchType.EAGER)
	@JoinTable(name = "student_courses",
	joinColumns = @JoinColumn(name="student_email"),
	inverseJoinColumns = @JoinColumn(name="course_id"))
	@Exclude
	Set<Course> course = new TreeSet<>();

	public void addCourse(Course t) {
		course.add(t);
		t.getStudent().add(this);
	
	}
	
	//getters and setters

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Course> getCourse() {
		return course;
	}

	public void setCourse(Set<Course> course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "Student email=" + email + ", name=" + name + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(course, email, name, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(course, other.course) && Objects.equals(email, other.email)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password);
	}

	
	
	

}
