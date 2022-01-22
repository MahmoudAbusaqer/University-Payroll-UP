package com.projects.up.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.projects.up.entities.TimeCard;

public interface TimeCardRepository extends CrudRepository<TimeCard, Integer> {
	@Override
	public List<TimeCard> findAll();
}
