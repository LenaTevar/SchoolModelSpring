package com.tevar.sms.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.tevar.sms.models.SchoolSubject;

/**
 * Using @Transactional to avoid problems while manipulating the DB. 
 * Be sure to use different properties when testing. 
 *
 */
@Transactional
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class SchoolSubjectRepositoryTest {
	@Autowired SchoolSubjectRepository ssRepo;
	
	@Test
	public void schoolSubjectAddAndRetrieveByIdRepositoryTest() {
		System.out.println(">> Test SchoolSubject Repository: Retrieving by id");
		printRepo();
		SchoolSubject test = new SchoolSubject("type1", "title1", 1);
		assertNotNull(ssRepo.save(test));
		System.out.println("\tAdding...");
		printRepo();
		long id =  test.getId();
		Optional<SchoolSubject> resultOpt = ssRepo.findById(id);
		assertTrue(resultOpt.isPresent());
		SchoolSubject result = resultOpt.get();
		assertEquals(id, result.getId());
	}
	
	@Test
	public void schoolSubjectAddAndRetrieveByNameRepositoryTest() {
		System.out.println(">> Test SchoolSubject Repository: Retrieving by name");
		printRepo();
		SchoolSubject test = new SchoolSubject("type2", "title2", 2);
		ssRepo.save(test);
		System.out.println("\tAdding...");
		printRepo();
		Optional<SchoolSubject> resultOpt = ssRepo.findByTypeAndTitleAndGrade("type2", 
				"title2", 2);
		assertTrue(resultOpt.isPresent());
		SchoolSubject result = resultOpt.get();
		assertEquals(test.getId(), result.getId());
		
	}
	
	@Test
	public void schoolSubjectRetrieveFailRepositoryTest(){
		System.out.println(">> Test SchoolSubject Repository: Retrieving fail");

		long id =  999999;
		Optional<SchoolSubject> resultOpt = ssRepo.findById(id);
		assertTrue(resultOpt.isEmpty());;
	}
	
	@Test
	public void schoolSubjectAddAndDeleteRepositoryTest() {
		System.out.println(">> Test SchoolSubject Repository: Deleting title4" );
		SchoolSubject test = new SchoolSubject("type4", "title4", 4);
		ssRepo.save(test);
		printRepo();
		System.out.println("\tDeleting...");
		long id =  test.getId();
		ssRepo.deleteById(id);
		printRepo();
		Optional<SchoolSubject> resultOpt = ssRepo.findById(id);
		assertTrue(resultOpt.isEmpty());
		
	}
	@Test
	public void schoolSubjectUpdateRepositoryTest() {
		System.out.println(">> Test SchoolSubject Repository: Updating Bio");
		SchoolSubject test = new SchoolSubject("Mandatory", "Bio", 4);
		ssRepo.save(test);
		printRepo();
		ssRepo.findByTypeAndTitleAndGrade("Mandatory", "Bio", 4).map(subject -> {
			subject.setTitle("Biologi");
			return ssRepo.save(subject);
		});
		
		System.out.println("      >>Result change Bio for Biologi: ");
		printRepo();
		assertTrue( ssRepo.findByTypeAndTitleAndGrade("Mandatory", "Biologi", 4).isPresent());
	}
	public void printRepo() {
		for (SchoolSubject s : ssRepo.findAll()) {
			System.out.println("\t"+s.toString());
		}
	}
}
