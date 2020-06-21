package com.tevar.sms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import com.tevar.sms.models.SchoolClass;

/**
 * You can write the return class from this repository
 * or return an optional in custom made queries.
 */
public interface SchoolClassRepository extends JpaRepository<SchoolClass, Long>, JpaSpecificationExecutor<SchoolClass>{
	Optional<SchoolClass> findByName(String name);
	
	@Query("SELECT e FROM SchoolClass e JOIN FETCH e.grades WHERE e.id = (:id)")
	Optional<SchoolClass> findById(@Param("id") long id);
}
