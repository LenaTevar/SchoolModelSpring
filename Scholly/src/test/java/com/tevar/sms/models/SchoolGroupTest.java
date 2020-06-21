package com.tevar.sms.models;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class SchoolGroupTest {
	

	
	@Test
	public void schoolGroupCoverageTest() {
		System.out.println(">> Test SchoolGroup coverage: constructor");
		SchoolGroup test = new SchoolGroup();
		assertNotNull(test.getId());
		test = new SchoolGroup("Group test");
		assertEquals("Group test", test.getName());		
	}
	
	@Test
	public void schoolGroupAddItemsCoverageTest() {
		System.out.println(">> Test SchoolGroup coverage: Add Classes, Subjects, Students");
		SchoolGroup test = new SchoolGroup();
		SchoolClass classTest = new SchoolClass();
		test.addClasses(classTest);
		assertTrue(test.getClasses().contains(classTest));
		
		SchoolSubject subj = new SchoolSubject();
		test.addSubjects(subj);
		assertTrue(test.getSubjects().contains(subj));
		
		Student student = new Student();
		test.addStudents(student);
		assertTrue(test.getStudents().contains(student));
		
	}
	
	@Test
	public void schoolGroupGettersSettersCoverageTest() {
		System.out.println(">> Test SchoolGroup coverage: Getters/Setters");
		SchoolGroup test = new SchoolGroup();
		test.setId(1000);
		assertEquals(1000, test.getId());
		
		test.setName("test");
		assertEquals("test", test.getName());
		
		test.setMaxStudents(10);
		assertEquals(10, test.getMaxStudents());
		test.setMinStudents(1);
		assertEquals(1, test.getMinStudents());
		
		Set<Student> s = new HashSet<Student>();
		s.add(new Student());
		test.setStudents(s);
		assertEquals(s, test.getStudents());
		
		Set<SchoolSubject> ss = new HashSet<SchoolSubject>();
		ss.add(new SchoolSubject());
		test.setSubjects(ss);
		assertEquals(ss, test.getSubjects());
		
		Set<SchoolClass> sc = new HashSet<SchoolClass>();
		sc.add(new SchoolClass());
		test.setClasses(sc);
		assertEquals(sc, test.getClasses());
		
	}
	
	@Test
	public void schoolGRoupToStringTest() {
		System.out.println(">> Test SchoolGroup coverage: to string");
		SchoolGroup test = new SchoolGroup("Testing");
		System.out.println("\t"+test.toString());
		String str = "SchoolGroup (id: 0, name: Testing, Classes: 0, Subjects: 0, Students: 0)";
		assertEquals(str, test.toString());
	}

}
