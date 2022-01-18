package com.projects.up.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.projects.up.entities.PersonnelDepartment;

public interface PersonnelDepartmentRepository extends CrudRepository<PersonnelDepartment, Integer> {
	@Override
	public List<PersonnelDepartment> findAll();
}
