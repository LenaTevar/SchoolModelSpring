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
public class SchoolClassTest {
	
	@Test
	public void schoolClassCoverageConstructorsTest() {
		System.out.println(">> Test SchoolClass: Constructors");
		SchoolClass class0 = new SchoolClass();
		SchoolClass class9 = new SchoolClass("test", 1);
		assertNotNull(class0.getId());
		assertNotNull(class9.getId());
	}
	@Test
	public void schoolClassCoverageAddGrade() {
		System.out.println(">> Test SchoolClass: Add grade");
		SchoolClass class1 = new SchoolClass();
		
		class1.addGrade(1);
		assertEquals(1, class1.getGrades().size());
		Set<Integer> g = class1.getGrades();
		int i = g.iterator().next();
		
		assertEquals(1, i);
	}
	
	@Test
	public void schoolClassCoverageAddStudent() {
		System.out.println(">> Test SchoolClass: Add Student");
		SchoolClass class2 = new SchoolClass("Class 2", 2);
		Student student = new Student("Ana", "Banana", class2);
		class2.addStudent(student);
		assertTrue(class2.getStudents().contains(student));
	}

	@Test
	public void schoolClassGettersSettersCoverageTest() {
		System.out.println(">> Test SchoolClass: Getters/Setters");
		SchoolClass test = new SchoolClass();
		
		test.setId(1000);
		assertEquals(1000, test.getId());
		
		test.setName("test");
		assertEquals("test", test.getName());
		
		Set<Student> s = new HashSet<Student>();
		s.add(new Student());
		test.setStudents(s);
		assertEquals(s, test.getStudents());
		
		Set<Integer> g = new HashSet<Integer>();
		g.add(1);
		test.setGrades(g);
		assertEquals(g, test.getGrades());
		
		Set<SchoolGroup> sg = new HashSet<SchoolGroup>();
		sg.add(new SchoolGroup());
		test.setGroups(sg);
		assertEquals(sg, test.getGroups());	
	
	}
	
	@Test
	public void schoolClassToStringCoverageTest() {
		System.out.println(">> Test SchoolClass: ToString");
		String expected = "SchoolClass ( id: 0, name: test, grade: 1, students: 0, groups: 0)";
		SchoolClass test = new SchoolClass("test", 1);
		
		assertEquals(expected, test.toString());
	
		System.out.println(test.toString());
		
	}
	
	@Test
	public void schoolClassRemoveStudentTest() {
		System.out.println(">> Test SchoolClass: add/remove student");
		SchoolClass test = new SchoolClass("test", 1);
		Student testStudent = new Student();
		test.addStudent(testStudent);
		assertEquals(1, test.getStudents().size());
		test.removeStudent(testStudent);
		assertEquals(0, test.getStudents().size());
		
	}

}
