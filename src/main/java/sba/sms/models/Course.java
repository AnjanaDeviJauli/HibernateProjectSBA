package sba.sms.models;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString.Exclude;


@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "course")
public class Course {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;
  @NonNull
  String name;
  @NonNull
  String instructor;
  @Exclude
 @ManyToMany(mappedBy = "course",
			cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
  List<Student> student = new LinkedList<>();
  
  //getter and setter
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
public String getInstructor() {
	return instructor;
}
public void setInstructor(String instructor) {
	this.instructor = instructor;
}
public List<Student> getStudent() {
	return student;
}
public void setStudent(List<Student> student) {
	this.student = student;
}
@Override
public String toString() {
	return "Course [id=" + id + ", name=" + name + ", instructor=" + instructor +  "]";
}
@Override
public int hashCode() {
	return Objects.hash(id, instructor, name, student);
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Course other = (Course) obj;
	return id == other.id && Objects.equals(instructor, other.instructor) && Objects.equals(name, other.name)
			&& Objects.equals(student, other.student);
}


  
  
}
