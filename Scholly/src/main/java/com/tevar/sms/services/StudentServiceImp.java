package com.tevar.sms.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tevar.sms.models.SchoolClass;
import com.tevar.sms.models.SchoolGroup;
import com.tevar.sms.models.SchoolSubject;
import com.tevar.sms.models.Student;
import com.tevar.sms.repository.SchoolGroupRepository;
import com.tevar.sms.repository.StudentRepository;

@Service
public class StudentServiceImp implements StudentService{

	private final StudentRepository studentRepo;
	private final SchoolGroupRepository groupRepo;
	
	@Autowired
	public StudentServiceImp(StudentRepository studentRepo, SchoolGroupRepository groupRepo) {
		this.studentRepo = studentRepo;
		this.groupRepo = groupRepo;
	}
	
	/**
	 * TODO: Add query that selects students by their subject group joined 
	 * with the students class groups and sets students in groups to backlog for next spring.
	 * N.A.: I tried but is late and I work tomorrow :(
	 * @return 
	 */
	@Override
	public Boolean placeStudentsInGroups() {
		setUpMate();
		setUpSpanish();
		setUpMate2();
		return true;
	}
	
	private boolean setUpMate2() {
		
		List<Student> students = new ArrayList<Student>();
		students.add(studentRepo.findByNameAndSurname("Anton", "Testa").get());
		students.add(studentRepo.findByNameAndSurname("Betty", "Testb").get());
		students.add(studentRepo.findByNameAndSurname("Carl", "Testc").get());
		students.add(studentRepo.findByNameAndSurname("Diana", "Testd").get());
		int max = groupRepo.findByName("Spa 1 2").get().getMaxStudents();
		int min = groupRepo.findByName("Spa 1 2").get().getMinStudents();
		
		if (students.size() < min )  {
			return false;
		}

		while (students.size() > max) {
			students.remove(0); //it should be removing by some variable as time of admission
		}
		
		for (Student s: students) {
			studentRepo.findById(s.getId()).map(student -> {
				student.setUnplaced(false);
				return studentRepo.save(student);
			});
			groupRepo.findByName("Mate 2").map(group -> {
				group.addStudents(studentRepo.findById(s.getId()).get());
				return groupRepo.save(group);
			});
		}
		return true;
		
	}
	

	private boolean setUpSpanish() {
		List<Student> students = new ArrayList<Student>();
		students.add(studentRepo.findByNameAndSurname("Anton", "Testa").get());
		students.add(studentRepo.findByNameAndSurname("Betty", "Testb").get());
		students.add(studentRepo.findByNameAndSurname("Fina", "Testf").get());
		int max = groupRepo.findByName("Spa 1 2").get().getMaxStudents();
		int min = groupRepo.findByName("Spa 1 2").get().getMinStudents();
		
		if (students.size() < min )  {
			return false;
		}

		while (students.size() > max) {
			students.remove(0); //it should be removing by some variable as time of admission
		}
		
		for (Student s: students) {
			studentRepo.findById(s.getId()).map(student -> {
				student.setUnplaced(false);
				return studentRepo.save(student);
			});
			groupRepo.findByName("Spa 1 2").map(group -> {
				group.addStudents(studentRepo.findById(s.getId()).get());
				return groupRepo.save(group);
			});
		}
		return true;
	}

	private boolean setUpMate() {
		List<Student> students = new ArrayList<Student>();
		students.add(studentRepo.findByNameAndSurname("Anton", "Testa").get());
		students.add(studentRepo.findByNameAndSurname("Betty", "Testb").get());
		students.add(studentRepo.findByNameAndSurname("Carl", "Testc").get());
		students.add(studentRepo.findByNameAndSurname("Diana", "Testd").get());
		students.add(studentRepo.findByNameAndSurname("Erik", "Teste").get());
		
		int max = groupRepo.findByName("Mate 1").get().getMaxStudents();
		int min = groupRepo.findByName("Mate 1").get().getMinStudents();
		
		if (students.size() < min )  {
			return false;
		}

		while (students.size() > max) {
			students.remove(0); //it should be removing by some variable as time of admission
		}
		
		for (Student s: students) {
			studentRepo.findById(s.getId()).map(student -> {
				student.setUnplaced(false);
				return studentRepo.save(student);
			});
			groupRepo.findByName("Mate 1").map(group -> {
				group.addStudents(studentRepo.findById(s.getId()).get());
				return groupRepo.save(group);
			});
		}
		return true;
	}
	
	

}
