package com.tevar.sms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tevar.sms.models.*;

public interface SchoolSubjectRepository extends JpaRepository<SchoolSubject, Long>, JpaSpecificationExecutor<SchoolSubject>{

	Optional<SchoolSubject> findByTypeAndTitleAndGrade(String type, String title, int grade);


}
