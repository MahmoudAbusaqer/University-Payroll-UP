package com.projects.up.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.projects.up.entities.FulltimePayments;

public interface FulltimePaymentsRepository extends CrudRepository<FulltimePayments, Integer> {
	@Override
	public List<FulltimePayments> findAll();
}
