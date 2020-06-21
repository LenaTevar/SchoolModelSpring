package com.tevar.sms.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.tevar.sms.models.SchoolGroup;
/**
 * Using @Transactional to avoid problems while manipulating the DB. 
 * Be sure to use different properties when testing. 
 *
 */
@Transactional
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class SchoolGroupRepositoryTest {
	
	@Autowired SchoolGroupRepository sgRepo;
	
	@Test
	public void addGroupAndFindById() {	
		System.out.println(">> Test SchoolGroup Repository: Adding and Retrieving by id");
		printRepo();
		SchoolGroup group = new SchoolGroup("Group");
		System.out.println("\tAdding...");
		assertNotNull(sgRepo.save(group));
		printRepo();
		long id = group.getId();
		
		Optional<SchoolGroup> resultId = sgRepo.findById(id);
		assertTrue(resultId.isPresent());
		SchoolGroup resultOptional = resultId.get();
		assertEquals(id, resultOptional.getId());
		System.out.println("\t"+resultOptional.toString());
	}

	@Test
	public void addGroupAndFindByName() {
		System.out.println(">> Test SchoolGroup Repository: Adding and Retrieving by id");
		printRepo();
		System.out.println("\tAdding...");
		SchoolGroup group = new SchoolGroup("Group 2");
		System.out.println("\t"+group.toString());
		assertNotNull(sgRepo.save(group));
		printRepo();
		long id = group.getId();
		
		Optional<SchoolGroup> resultId = sgRepo.findByName("Group 2");
		assertTrue(resultId.isPresent());
		SchoolGroup resultOptional = resultId.get();
		assertEquals(id, resultOptional.getId());
		System.out.println("\t"+resultOptional.toString());
		
	}
	
	@Test
	public void removeGroupTest() {
		System.out.println(">> Test SchoolGroup Repository: Adding and Deleting by id");
		SchoolGroup group = new SchoolGroup("Group 3");
		System.out.println("\tAdding: "+group.toString());
		assertNotNull(sgRepo.save(group));
		printRepo();
		long id = group.getId();
		
		sgRepo.deleteById(id);
		System.out.println("\tDeleting...");
		printRepo();
		Optional<SchoolGroup> result = sgRepo.findById(id);
		assertTrue(result.isEmpty());
	}
	
	@Test
	public void groupUpdateRepositoryTest() {
		System.out.println(">> Test SchoolSubject Repository: Updating Bio");
		SchoolGroup group = new SchoolGroup("EDIT");
		sgRepo.save(group);
		printRepo();
		
		sgRepo.findByName("EDIT").map(g ->{
			g.setName("Group 11");
			return sgRepo.save(g);
		});
		
		System.out.println("      >>Result change EDIT for Group 11: ");
		printRepo();
		assertTrue(sgRepo.findByName("Group 11").isPresent());
	}
	public void printRepo() {
		for (SchoolGroup s : sgRepo.findAll()) {
			System.out.println("\t"+s.toString());
		}
	}
}
