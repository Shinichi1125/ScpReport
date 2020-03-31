package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Report;

public interface ReportDao {	
	List<Report> findAll();
	Optional<Report> findById(int id);
	void insert(Report report);
	int update(Report report);
	int deleteById(int id);
}
