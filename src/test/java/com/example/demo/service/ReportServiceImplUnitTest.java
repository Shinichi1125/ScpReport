package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional; 

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import com.example.demo.entity.Report;
import com.example.demo.repository.ReportDao;

@ExtendWith(MockitoExtension.class)
@DisplayName("Unit test for ReportServiceImpl")
class ReportServiceImplUnitTest {
	
	@Mock  //Mock class object
	private ReportDao dao; 
	
	@InjectMocks  
	private ReportServiceImpl reportServiceImpl; 
	
	@Test 
	@DisplayName("Test case where the find result is 0")
	void testFindAllReturnEmptyList() {
		List<Report> list = new ArrayList<>();
		
		// set I/O of the mock class
		when(dao.findAll()).thenReturn(list);
		
		// execute the service
		List<Report> actualList = reportServiceImpl.findAll();
		
		// check the number of execution of the mock's method
		verify(dao, times(1)).findAll();
		
		// check the return value (expected, actual) 
		assertEquals(0, actualList.size());
	}
	
	@Test 
	@DisplayName("Test case where the findAll()'s result is 2")
	void testFindAllReturnList() {
		List<Report> list = new ArrayList<>();
		Report report1 = new Report();
		Report report2 = new Report(); 
		list.add(report1);
		list.add(report2);
		
		// set the mock class's I/O
		when(dao.findAll()).thenReturn(list);
		
		// execute the service
		List<Report> actualList = reportServiceImpl.findAll();
		
		// check the number of the mock's method's execution
		verify(dao, times(1)).findAll();
		
		// check the return value (expected, actual) 
		assertEquals(2, actualList.size());
	}
	
	@Test 
	@DisplayName("Test case where no report can be fetched")
	void testGetReportThrowException() {
		// set the mock class's I/O
		when(dao.findById(0)).thenThrow(new EmptyResultDataAccessException(1));
		
		// check if ReportNotFoundException gets thrown when a report cannot be fetched
		try {
			Optional<Report> reportO = reportServiceImpl.getReport(0);
		} catch(ReportNotFoundException e) {
			assertEquals(e.getMessage(), "The designated report does not exist"); 
		}
	}
	
	@Test
	@DisplayName("Test case where 1 report gets fetched")
	void testGetReportReturnOne() {
		// instantiate a report with a default value 
		Report report = new Report(); 
		Optional<Report> reportOpt = Optional.ofNullable(report);
		
		// set the mock class's I/O
		when(dao.findById(1)).thenReturn(reportOpt);
		
		// execute the service 
		Optional<Report> reportActual = reportServiceImpl.getReport(1);
		
		// check the number of the mock's method's executions
		verify(dao, times(1)).findById(1); 
		
		// check if the report exists 
		assertTrue(reportActual.isPresent());
	}
	
	@Test 
	@DisplayName("Test case that makes sure that the method doesn't get executed in case the id doesn't exist")
	void throwNotFoundException() {
		// set the mock class's I/O
		when(dao.deleteById(0)).thenReturn(0);
		
		// check if an exception gets thrown when there is nothing to delete 
		try {
			reportServiceImpl.deleteById(0);
		} catch(ReportNotFoundException e){
			assertEquals(e.getMessage(), "The report to be deleted does not exist");
		}
	}
}
