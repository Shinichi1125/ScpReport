package com.example.demo.app.report;

import java.time.LocalDateTime;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.entity.Files;

public class ReportForm {
	
	
	//private int reportId; 
	
	@Digits(integer = 1, fraction = 0)
	private int threatId;
	
	@NotNull(message = "Enter a report title")
	@Size(min = 1, max = 40, message="The maximum title length is 40 characters")
	private String title; 
	
	@NotNull(message = "Enter a description of the case")
	private String detail; 
	
	@NotNull(message = "Specify the date and time")
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
	private LocalDateTime reportedDate;
	
	public boolean isNewReport;
	
	private Files file;

	public ReportForm() {
		
	}
	
	public ReportForm(//int reportId,
							 int threatId, 
							 String title,
							 String detail,
							 LocalDateTime reportedDate,
							 boolean isNewReport,
							 Files file) {
		//this.reportId = reportId;
		this.threatId = threatId;
		this.title = title; 
		this.detail = detail; 
		this.reportedDate = reportedDate; 
		this.isNewReport = isNewReport; 
		this.file = file;
	}
	
//	public int getReportId() {
//		return reportId; 
//	}
//	
//	public void setReportId(int reportId) {
//		this.reportId = reportId; 
//	}

	public int getThreatId() {
		return threatId;
	}

	public void setThreatId(int threatId) {
		this.threatId = threatId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public LocalDateTime getReportedDate() {
		return reportedDate;
	}

	public void setReportedDate(LocalDateTime reportedDate) {
		this.reportedDate = reportedDate;
	}

	public boolean isNewReport() {
		return isNewReport;
	}

	public void setNewReport(boolean isNewReport) {
		this.isNewReport = isNewReport;
	} 
	
	public Files getFile() {
		return file;
	}
	
	public void setFile(Files file) {
		this.file = file; 
	}
	
}
