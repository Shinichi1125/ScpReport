package com.example.demo.entity;

import java.time.LocalDateTime;

public class Report {
	
	private int reportId;
	private String title; 
	private int threatLevel;
	private LocalDateTime reportDate;
	private String description;
	//private Img img;  // to be added later
//	private int reporterId;
//	private String reporterName;
	
	public Report() {
		
	}

	public int getReportId() {
		return reportId;
	}

	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getThreatLevel() {
		return threatLevel;
	}

	public void setThreatLevel(int threatLevel) {
		this.threatLevel = threatLevel;
	}

	public LocalDateTime getReportDate() {
		return reportDate;
	}

	public void setReportDate(LocalDateTime reportDate) {
		this.reportDate = reportDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public int getReporterId() {
//		return reporterId;
//	}
//
//	public void setReporterId(int reporterId) {
//		this.reporterId = reporterId;
//	}
//
//	public String getReporterName() {
//		return reporterName;
//	}
//
//	public void setReporterName(String reporterName) {
//		this.reporterName = reporterName;
//	} 
	
}

//Report ID: reportId
//Threat level: (1: Safe, 2: Euclid, 3:  Keter, 4: Neutralized, 5: Explained, 6: Thaumiel)
//Reported Date: Date
//Description: Text
//Image: img
//Reporter ID: reporterID
//Reporter Name: reporterName
