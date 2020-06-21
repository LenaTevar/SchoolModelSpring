package com.tevar.sms.models;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
 
@Entity
@Table(name = "schoolgroup")
public class SchoolGroup {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private int minStudents;
	private int maxStudents;
	/**
	 * A Set for any kind of school will not matter for big schools
	 * and be able to fit many classes for small schools.
	 * Using fetch eager after testing with lazy and failed.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="schoolgroup_schoolclasses",
	joinColumns = {
			@JoinColumn(name="schoolgroup_id")
	},
	inverseJoinColumns = {
			@JoinColumn(name="schoolclasses_id")
	})
	private Set<SchoolClass> classes = new HashSet<SchoolClass>();
	
	/**
	 * Using fetch eager after testing with lazy and failed.
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="schoolgroup_schoolsubjects",
	joinColumns = {
			@JoinColumn(name="schoolgroup_id")
	},
	inverseJoinColumns = {
			@JoinColumn(name="schoolsubjects_id")
	})
	private Set<SchoolSubject> subjects = new HashSet<SchoolSubject>();
	
	/**
	 * Using fetch eager after testing with lazy and failed. 
	 */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="schoolgroup_students",
	joinColumns = {
			@JoinColumn(name="schoolgroup_id")
	},
	inverseJoinColumns = {
			@JoinColumn(name="students_id")
	})
	private Set<Student> students = new HashSet<Student>();
	
	@Override
	public String toString() {
		String str = String.format("SchoolGroup (id: %d, name: %s, Classes: %d, Subjects: %d, Students: %d)", 
				getId(), getName(), this.classes.size(), this.classes.size(), this.subjects.size());
		return str;
	}
	
	SchoolGroup(){}
	public SchoolGroup(String name) {
		this.name = name;
	}

	public void addClasses (SchoolClass classToAdd) {
		this.classes.add(classToAdd);
	}
	public void addSubjects(SchoolSubject subjectToAdd) {
		this.subjects.add(subjectToAdd);
	}
	public void addStudents(Student studentToAdd) {
		this.students.add(studentToAdd);
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
	public Set<SchoolClass> getClasses() {
		return classes;
	}
	public void setClasses(Set<SchoolClass> classes) {
		this.classes = classes;
	}
	public Set<SchoolSubject> getSubjects() {
		return subjects;
	}
	public void setSubjects(Set<SchoolSubject> subjects) {
		this.subjects = subjects;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public int getMinStudents() {
		return minStudents;
	}

	public void setMinStudents(int minStudents) {
		this.minStudents = minStudents;
	}

	public int getMaxStudents() {
		return maxStudents;
	}

	public void setMaxStudents(int maxStudents) {
		this.maxStudents = maxStudents;
	}
	
	
}
