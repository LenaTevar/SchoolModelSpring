package com.tevar.sms.models;

import java.util.HashSet;
import java.util.Set;
 
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="students")
public class Student {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String surname;
	private Boolean unplaced;

	@ManyToOne()
	@JoinColumn(name="schoolclass_id")
	private SchoolClass schoolClass;
	/**
	 * Using Fetch type Eager after test failed. 
	 * Not using cascade to avoid detached problems with the parent class. 
	 */
	@ManyToMany(fetch= FetchType.EAGER, mappedBy="students")
	private Set<SchoolSubject> subjects = new HashSet<SchoolSubject>();
	
	@Override
	public String toString() {
		String str = String.format("Student (id: %d name: %s, surname: %s, SchoolClass: %d, Subjects Size: %d)", 
				id, name, surname, schoolClass.getId(), subjects.size());
		return str;
	}
	
	Student(){}
	public Student(String name, 
			String surname, 
			SchoolClass schoolClass) {
		this.name = name;
		this.surname = surname;
		this.schoolClass = schoolClass;
		this.unplaced = true;
	}
	
	public void addSubject(SchoolSubject s) {
		if (!this.subjects.contains(s)) {
			this.subjects.add(s);			
		}
		
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
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public SchoolClass getSchoolClass() {
		return schoolClass;
	}
	public void setSchoolClass(SchoolClass schoolClass) {
		this.schoolClass = schoolClass;
	}
	public Set<SchoolSubject> getSubjects() {
		return subjects;
	}
	public void setSubjects(Set<SchoolSubject> subjects) {
		this.subjects = subjects;
	}

	public Boolean getUnplaced() {
		return unplaced;
	}

	public void setUnplaced(Boolean unplaced) {
		this.unplaced = unplaced;
	}

}
