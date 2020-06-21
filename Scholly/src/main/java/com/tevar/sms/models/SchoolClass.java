package com.tevar.sms.models;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table; 

@Entity
@Table(name = "schoolclass")
public class SchoolClass {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name="grade")
	private Set<Integer> grades = new HashSet<Integer>();

	@OneToMany(fetch = FetchType.EAGER, mappedBy="schoolClass",cascade = CascadeType.ALL)
	private Set<Student> students = new HashSet<Student>();
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="classes")
	private Set<SchoolGroup> groups = new HashSet<SchoolGroup>();
	


	SchoolClass(){}

	public SchoolClass(String name, int grade) {
		this.name = name;
		this.grades.add(grade);
	}
	/**
	 * Setting this as SchoolClass to avoid errors with 
	 * Detached entities in h2. 
	 * @param student
	 */
	public void addStudent(Student student) {
		this.students.add(student);
		student.setSchoolClass(this); 
	}
	/**
	 * Setting null as SchoolClass to avoid errors with 
	 * Detached entities in h2. 
	 * @param student
	 */
	public void removeStudent(Student student) {
		student.setSchoolClass(null);
		this.students.remove(student);
	}
	
	public void addGrade(int grade) {
		this.grades.add(grade);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Integer> getGrades() {
		return grades;
	}
	public void setGrades(Set<Integer> grades) {
		this.grades = grades;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	public Set<SchoolGroup> getGroups() {
		return groups;
	}
	public void setGroups(Set<SchoolGroup> groups) {
		this.groups = groups;
	}
	 @Override
	 public String toString() {		 
		 String str = String.format("SchoolClass ( id: %d, name: %s, grade: %d, students: %d, groups: %d)", 
				 getId(), getName(), grades.size(), students.size(), groups.size());
		 return str;
	 }
	
}
