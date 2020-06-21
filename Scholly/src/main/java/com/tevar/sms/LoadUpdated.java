package com.tevar.sms;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.tevar.sms.models.SchoolClass;
import com.tevar.sms.models.SchoolGroup;
import com.tevar.sms.models.SchoolSubject;
import com.tevar.sms.models.Student;
import com.tevar.sms.repository.SchoolClassRepository;
import com.tevar.sms.repository.SchoolGroupRepository;
import com.tevar.sms.repository.SchoolSubjectRepository;
import com.tevar.sms.repository.StudentRepository;
import com.tevar.sms.services.StudentServiceImp;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class LoadUpdated {
	  @Bean
	  CommandLineRunner initDatabase(SchoolClassRepository schoolclassRepo,
			  SchoolSubjectRepository schoolsubjectRepo,
			  SchoolGroupRepository schoolgroupRepo,
			  StudentRepository studentRepo,
			  StudentServiceImp studentImp) {
	    return args -> {
	    	addClasses(schoolclassRepo);
	    	addStudents(schoolsubjectRepo,schoolclassRepo,schoolgroupRepo,studentRepo);
	    	addSubjects(schoolsubjectRepo,schoolclassRepo, studentRepo);
	    	addGroups(schoolsubjectRepo,schoolclassRepo,schoolgroupRepo);
	    	fixStudents(schoolsubjectRepo,schoolclassRepo,schoolgroupRepo,studentRepo, studentImp);
	    };
	  }

	private void fixStudents(SchoolSubjectRepository schoolsubjectRepo, SchoolClassRepository schoolclassRepo,
			SchoolGroupRepository schoolgroupRepo, StudentRepository studentRepo,StudentServiceImp studentImp) {
		System.out.println(">> Fixing Groups");
		studentImp.placeStudentsInGroups();
	}

	private void addStudents(SchoolSubjectRepository schoolsubjectRepo, SchoolClassRepository schoolclassRepo,
			SchoolGroupRepository schoolgroupRepo, StudentRepository studentRepo) {
		System.out.println(">> Adding Students");
		Student FristGradeA = new Student("Anton", "Testa", schoolclassRepo.findByName("First").get());
		Student FirstGradeB = new Student("Betty", "Testb",schoolclassRepo.findByName("First").get());
		Student FirstGradeC = new Student("Carl", "Testc",schoolclassRepo.findByName("First").get());
		Student FirstGradeD = new Student("Diana", "Testd",schoolclassRepo.findByName("First").get());
		Student FirstGradeE = new Student("Erik", "Teste",schoolclassRepo.findByName("First").get());
		
		studentRepo.save(FristGradeA);
		studentRepo.save(FirstGradeB);
		studentRepo.save(FirstGradeC);
		studentRepo.save(FirstGradeD);
		studentRepo.save(FirstGradeE);
		
		
		Student FristGradeF = new Student("Fina", "Testf", schoolclassRepo.findByName("Second").get());
		studentRepo.save(FristGradeF);
		
	}

	private void addGroups(SchoolSubjectRepository schoolsubjectRepo, SchoolClassRepository schoolclassRepo,
			SchoolGroupRepository schoolgroupRepo) {
		System.out.println(">> Adding Groups");
		SchoolGroup schoolGroupMate1 = new SchoolGroup("Mate 1");
		schoolGroupMate1.setMaxStudents(4);
		schoolGroupMate1.setMinStudents(2);
		schoolGroupMate1.addSubjects(schoolsubjectRepo.findByTypeAndTitleAndGrade("M", "Mate", 1).get());
		schoolGroupMate1.addClasses(schoolclassRepo.findByName("First").get());
		
		SchoolGroup schoolGroupSpa12 = new SchoolGroup("Spa 1 2");
		schoolGroupSpa12.setMaxStudents(4);
		schoolGroupSpa12.setMinStudents(1);
		schoolGroupSpa12.addSubjects(schoolsubjectRepo.findByTypeAndTitleAndGrade("I", "Spa", 1).get());
		schoolGroupSpa12.addSubjects(schoolsubjectRepo.findByTypeAndTitleAndGrade("I", "Spa", 2).get());
		schoolGroupSpa12.addClasses(schoolclassRepo.findByName("First").get());
		schoolGroupSpa12.addClasses(schoolclassRepo.findByName("Second").get());
		
		SchoolGroup schoolGroupMate2 = new SchoolGroup("Mate 2");
		schoolGroupMate2.setMaxStudents(4);
		schoolGroupMate2.setMinStudents(2);
		schoolGroupMate1.addSubjects(schoolsubjectRepo.findByTypeAndTitleAndGrade("M", "Mate", 2).get());
		schoolGroupMate1.addClasses(schoolclassRepo.findByName("Second").get());
		
		schoolgroupRepo.save(schoolGroupMate1);
		schoolgroupRepo.save(schoolGroupSpa12);
		schoolgroupRepo.save(schoolGroupMate2); 
		
	}

	private void addSubjects(SchoolSubjectRepository schoolsubjectRepo, 
			SchoolClassRepository schoolclassRepo,
			StudentRepository studentRepo) {
		System.out.println(">> Adding Subjects");
		SchoolSubject schoolSubjectMate = new SchoolSubject("M", "Mate", 1);
		schoolSubjectMate.addStudent(studentRepo.findByNameAndSurname("Anton", "Testa").get());
		schoolSubjectMate.addStudent(studentRepo.findByNameAndSurname("Betty", "Testb").get());
		schoolSubjectMate.addStudent(studentRepo.findByNameAndSurname("Carl", "Testc").get());
		schoolSubjectMate.addStudent(studentRepo.findByNameAndSurname("Diana", "Testd").get());
		schoolSubjectMate.addStudent(studentRepo.findByNameAndSurname("Erik", "Teste").get());
		
		SchoolSubject schoolSubjectSpa = new SchoolSubject("I", "Spa", 1);
		schoolSubjectSpa.addStudent(studentRepo.findByNameAndSurname("Anton", "Testa").get());
		schoolSubjectSpa.addStudent(studentRepo.findByNameAndSurname("Betty", "Testb").get());
		
		SchoolSubject schoolSubjectMate2 = new SchoolSubject("M", "Mate", 2);
		schoolSubjectMate2.addStudent(studentRepo.findByNameAndSurname("Fina", "Testf").get());
		
		SchoolSubject schoolSubjectSpa2 = new SchoolSubject("I", "Spa", 2);
		schoolSubjectSpa2.addStudent(studentRepo.findByNameAndSurname("Fina", "Testf").get());
		
		schoolsubjectRepo.save(schoolSubjectMate);
		schoolsubjectRepo.save(schoolSubjectSpa);
		
		schoolsubjectRepo.save(schoolSubjectMate2);
		schoolsubjectRepo.save(schoolSubjectSpa2);
		
		
	}

	private void addClasses(SchoolClassRepository schoolclassRepo) {
		System.out.println(">> Adding Classes");
		SchoolClass schoolClassFirst = new SchoolClass("First", 1);
		SchoolClass schoolClassSecond = new SchoolClass("Second", 2);
		schoolclassRepo.save(schoolClassFirst);
		schoolclassRepo.save(schoolClassSecond);
		
	}

}
