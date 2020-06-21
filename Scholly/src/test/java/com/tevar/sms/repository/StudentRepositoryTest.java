package com.tevar.sms.repository;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import com.tevar.sms.models.SchoolClass;
import com.tevar.sms.models.Student;
/**
 * Using @Transactional to avoid problems while manipulating the DB. 
 * Be sure to use different properties when testing. 
 *
 */
@Transactional
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class StudentRepositoryTest {

	@Autowired StudentRepository studentRepo;
	
	@Autowired  SchoolClassRepository classRepo;

	
	@Test
	public void addStudentAndFindByIdPassTest() {	
		System.out.println(">> Test Student Repository: Find by Id");	
		printRepo();

		Student test = new Student("Ana", "Test", classRepo.findById(1).get());

		studentRepo.save(test);
		System.out.println("\tAdding...");
		printRepo();
		assertEquals(test.getId(), studentRepo.findById(test.getId()).get().getId());
		List<SchoolClass> aaaa = classRepo.findAll();
		for(SchoolClass a : aaaa) {
			System.out.println(a.toString());
		}
		
		
	}

	@Test
	public void addStudentAndFindByNameSurnamePassTest() {
		System.out.println(">> Test Student Repository: Find By variables");
		Student test = new Student("Bob", "Test", classRepo.findById(1).get());
		studentRepo.save(test);
		assertEquals(test.getId(), studentRepo.findByNameAndSurname("Bob", "Test").get().getId());
		
	
	}

	@Test
	public void retrieveStudentFromRepoFailedTest() {
		
		System.out.println(">> Test Student Repository: Retrieving by id return emtpy optional");		
		Optional<Student> test = studentRepo.findById((long) -1);			
		assertTrue(test.isEmpty());
	    
	}

	@Test
	public void deleteStudentFromRepoTest() {
		System.out.println(">> Test Student Repository: Deleting student");
		Student test = new Student("Deletus", "Test", classRepo.findById(1).get());
		studentRepo.save(test);
		printRepo();

		studentRepo.deleteById(studentRepo.findByNameAndSurname("Deletus", "Test").get().getId());
		System.out.println("\tDeleting Deletus Test");
		printRepo();

		
		assertTrue(studentRepo.findByNameAndSurname("Deletus", "Test").isEmpty());
	}

	@Test
	public void updateStudentFromRepoTest() {
		
		System.out.println(">> Test Student Repository: Updating student");
		Student test = new Student("Claire", "Test", classRepo.findById(1).get());
		System.out.println("\tSaving: " + test.toString());
		studentRepo.save(test);
		printRepo();

		
		studentRepo.findByNameAndSurname("Claire", "Test").map(student -> {
			student.setName("Cloe");
			return studentRepo.save(student);
		});

		System.out.println("      >>Result change Claire for Cloe: ");
		printRepo();

		assertTrue(studentRepo.findByNameAndSurname("Cloe", "Test").isPresent());
		
	}
	public void printRepo() {
		for (Student s : studentRepo.findAll()) {
			System.out.println("\t"+s.toString());
		}
	}
	
	
}
