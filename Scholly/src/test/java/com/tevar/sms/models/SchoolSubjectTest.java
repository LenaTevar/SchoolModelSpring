package com.tevar.sms.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class SchoolSubjectTest {
	
	@Test
	public void schoolSubjectCoverageConstructorsTest() {
		System.out.println(">> Test Subject coverage: constructor");
		SchoolSubject subj = new SchoolSubject();
		assertNotNull(subj.getId());
		
		subj = new SchoolSubject("mandatory", "Mate", 1);
		assertEquals(1, subj.getGrade());
		assertEquals("mandatory", subj.getType());
	}
	
	@Test
	public void schoolSubjectCoverageSetterTest() {
		System.out.println(">> Test Subject coverage: getters/setters");
		SchoolSubject subj = new SchoolSubject();
		subj.setId(1000);
		assertEquals(1000, subj.getId());
		
		subj.setType("type");
		assertEquals("type", subj.getType());
		
		subj.setTitle("title");
		assertEquals("title", subj.getTitle());
		
		subj.setGrade(1);
		assertEquals(1, subj.getGrade());
		
		Student s = new Student("test", "qwerty", new SchoolClass("Test", 1));
		subj.addStudent(s);
		assertEquals(1, subj.getStudents().size());
		
		Set<Student> st = new HashSet<Student>();
		st.add(new Student());
		subj.setStudents(st);
		assertEquals(st, subj.getStudents());
		
		Set<SchoolGroup> g = new HashSet<SchoolGroup>();
		g.add(new SchoolGroup());
		subj.setGroups(g);
		assertEquals(g, subj.getGroups());

	}
	
	@Test
	public void schoolSubjectCoverageToStringTest() {
		System.out.println(">> Test Subject coverage: to string");
		SchoolSubject sg = new SchoolSubject("type", "title", 1);
		System.out.println("\t" + sg.toString());
		String expected = "SchoolSubject ( id: 0, type: type, title: title, grade: 1, students: 0, groups: 0)";
		assertEquals(expected, sg.toString());
	}

}
