package com.tevar.sms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


import com.tevar.sms.models.SchoolGroup;


public interface SchoolGroupRepository extends JpaRepository<SchoolGroup, Long>, JpaSpecificationExecutor<SchoolGroup>{
	Optional<SchoolGroup> findByName(String name);
}
