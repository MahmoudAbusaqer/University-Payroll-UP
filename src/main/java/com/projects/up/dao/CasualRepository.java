package com.projects.up.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.projects.up.entities.Casual;

public interface CasualRepository extends CrudRepository<Casual, Integer> {
	@Override
	public List<Casual> findAll();
}
