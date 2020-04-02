package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Report;
import com.example.demo.repository.ReportDao;

@Service
public class ReportServiceImpl implements ReportService {
	
	private final ReportDao dao; 
	
	@Autowired
	public ReportServiceImpl(ReportDao dao) {
		this.dao = dao; 
	}

	@Override
	public List<Report> findAll() {
		return dao.findAll();
	}

	@Override
	public Optional<Report> getReport(int id) {
		
		try {
			return dao.findById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ReportNotFoundException("The designated report does not exist");
		}
	}

	@Override
	public void insert(Report report) {
		dao.insert(report);
	}

	@Override
	public void update(Report report) {
		
		if(dao.update(report) == 0) {
			throw new ReportNotFoundException("The report to be updated does not exist");
		}
	}

	@Override
	public void deleteById(int id) {

		if(dao.deleteById(id) == 0) {
			throw new ReportNotFoundException("The report to be deleted does not exist");
		}

	}

}
