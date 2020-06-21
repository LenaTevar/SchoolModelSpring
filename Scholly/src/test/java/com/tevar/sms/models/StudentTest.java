package com.tevar.sms.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class StudentTest {

	@Test
	public void studentCoverageConstructorsTest() {
		System.out.println(">> Test Student coverage: constructor");
				
		Student student1 = new Student();
		assertNotNull(student1.getId());
		
		SchoolClass myclass = new SchoolClass();
		Student student2 = new Student("Ana", "Banana", myclass);
		assertNotNull(student2.getId());

	}
	
	@Test
	public void studentCoverageSettersTest() {
		System.out.println(">> Test Student coverage: getters/setters");
		Student test = new Student();
		test.setName("Ana");
		assertEquals("Ana", test.getName());
		
		test.setSurname("Banana");
		assertEquals("Banana", test.getSurname());
		
		SchoolClass sc = new SchoolClass("test", 1);
		test.setSchoolClass(sc);
		assertEquals(sc, test.getSchoolClass());
		
		
		Set<SchoolSubject> setTest = new HashSet<SchoolSubject>();
		setTest.add(new SchoolSubject());
		test.setSubjects(setTest);
		assertEquals(1, test.getSubjects().size());
		
		test.setId((long)100);
		assertEquals(100, test.getId());
		
		test.setUnplaced(true);
		assertTrue(test.getUnplaced());
				
	}

	@Test
	public void studentCoverageAddSubjectTest() {
		System.out.println(">> Test Student coverage: adding subject");
		Student student = new Student("Ana", "Banana", new SchoolClass("Test", 1));
		SchoolSubject subj = new SchoolSubject("mandatory", "mate", 1);
		student.addSubject(subj);
		assertTrue(student.getSubjects().contains(subj));
	}
	
	@Test
	public void studentCoverageToString() {
		System.out.println(">> Test Student coverage: toString");
		SchoolClass sc = new SchoolClass("test", 1);
		Student student = new Student("Ana", "Banana", sc);
		System.out.println(student.toString());
		String toString = "Student (id: 0 name: Ana, surname: Banana, SchoolClass: 0, Subjects Size: 0)";
		assertEquals(toString, student.toString());
				
	}
	
	

}
