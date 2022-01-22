package com.projects.up.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.projects.up.entities.Leaves;

public interface LeavesRepository extends CrudRepository<Leaves, Integer>{
	@Override
	public List<Leaves> findAll();
}
