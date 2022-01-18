package com.projects.up.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.projects.up.entities.FullTime;

public interface FullTimeRepository extends CrudRepository<FullTime, Integer> {
	@Override
	public List<FullTime> findAll();
}
