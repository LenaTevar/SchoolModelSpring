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
@Table(name = "schoolsubject")

public class SchoolSubject {
	
	@Id
	@GeneratedValue
	private long id;
	private String type;
	private String title;
	private int grade;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="schoolsubject_students",
			joinColumns = {
					@JoinColumn(name="schoolsubject_id")
			},
			inverseJoinColumns = {
					@JoinColumn(name="students_id")
			})
	private Set<Student> students = new HashSet<Student>();
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy="subjects")
	private Set<SchoolGroup> groups = new HashSet<SchoolGroup>();
	

	SchoolSubject(){}

	public SchoolSubject(String type, String title, int grade) {
		this.type = type;
		this.grade = grade;
		this.title = title;
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
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


	
	public void addStudent(Student studentToAdd) {
		studentToAdd.addSubject(this);
		this.students.add(studentToAdd);
	}
	@Override
	public String toString() {
		String str = String.format("SchoolSubject ( id: %d, type: %s, title: %s, grade: %d, students: %d, groups: %d)",
				id, type, title, grade, this.students.size(), this.groups.size());
		return str;
	}
}
