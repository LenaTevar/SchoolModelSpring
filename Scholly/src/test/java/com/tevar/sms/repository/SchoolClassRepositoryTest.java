package com.tevar.sms.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import javax.transaction.Transactional;

import com.tevar.sms.models.SchoolClass;
/**
 * Using @Transactional to avoid problems while manipulating the DB. 
 * Be sure to use different properties when testing. 
 *
 */
@Transactional
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class SchoolClassRepositoryTest {
	
	@Autowired SchoolClassRepository scRepo;
	
	@Test
	public void addSchoolClassAndFindByIdPassTest() {
		System.out.println(">> Test SchoolClass Repository: Adding");
		SchoolClass test = new SchoolClass("test", 1);
		System.out.println("\tAdded: " + test.toString());
		assertNotNull(scRepo.save(test)); 
		printRepo();
		
		System.out.println(">> Test SchoolClass Repository: Retrieving by id");
		Optional<SchoolClass> resultId = scRepo.findById(test.getId());
		SchoolClass resultedClass = resultId.get();
		assertEquals(resultedClass.getId(), test.getId());			
	}
	
	@Test
	public void addSchoolClassAndFindByNameTest() {
		System.out.println(">> Test SchoolClass Repository: Adding");
		SchoolClass test = new SchoolClass("School", 1);
		System.out.println("\ttAdded:" + test.toString());
		assertNotNull(scRepo.save(test));
		printRepo();
		System.out.println(">> Test SchoolClass Repository: Retrieving by name");
		Optional<SchoolClass> resultId = scRepo.findByName("School");
		SchoolClass resultedClass = resultId.get();
		assertEquals(resultedClass.getId(), test.getId());	
		
	}
	@Test
	public void addSchoolClassAndFindFailTest() {		
		System.out.println(">> Test SchoolClass Repository: Retrieving by name and Fail");
		Optional<SchoolClass> resultId = scRepo.findByName("Potatis");
		assertTrue(resultId.isEmpty());	
		
	}
	@Test
	public void deleteSchoolClassTest() {
		System.out.println(">> Test SchoolClass Repository: Adding Wrong School");
		SchoolClass test = new SchoolClass("Wrong School", 1);
		System.out.println("\t" + test.toString());
		assertNotNull(scRepo.save(test));
		printRepo();
		System.out.println(">> Test SchoolClass Repository: Deleting");
		scRepo.deleteById(test.getId());
		printRepo();
		Optional<SchoolClass> result  = scRepo.findByName("Wrong School");
		assertTrue(result.isEmpty());
	}
	

	@Test
	public void updateSchoolClassTest() {
		System.out.println(">> Test SchoolClass Repository: Updating");
		SchoolClass test = new SchoolClass("Class", 1);
		System.out.println("\t" + test.toString());
		scRepo.save(test);
		printRepo();
		
		scRepo.findByName("Class").map(sc ->{
			sc.setName("Klass");
			return scRepo.save(sc);
		});
		
		System.out.println("      >>Result change Class for Klass: ");
		printRepo();
		
		assertTrue(scRepo.findByName("Klass").isPresent());
	}
	
	public void printRepo() {
		for (SchoolClass sc : scRepo.findAll()) {
			System.out.println("\t"+sc.toString());
		}
	}
	

}
