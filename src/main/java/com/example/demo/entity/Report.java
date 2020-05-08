package com.example.demo.entity;

import java.time.LocalDateTime;

public class Report {
	
	private int reportId;
	private String title; 
	private int threatLevel;
	private LocalDateTime reportDate;
	private String description;
	private String imgPath; 
	private String imgReqPath;
	private int userId;
	private String userName; 
	private User user;
	//private Img img;  // to be added later
	
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
	
	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	public String getImgReqPath() {
		String rawPath = getImgPath();
		String str = "";
		for(int i = 0; i < rawPath.length(); i++) {
			if(i > 2) {
				str = str + rawPath.charAt(i);
			}
		}
		imgReqPath = str; 
		return imgReqPath;
	}

	public void setImgReqPath(String imgReqPath) {
		this.imgReqPath = imgReqPath;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	} 
	
	public User getUser() {
		return user; 
	}
	
	public void setUser(User user) {
		this.user = user; 
	}
	
}

//Report ID: reportId
//Threat level: (1: Safe, 2: Euclid, 3:  Keter, 4: Neutralized, 5: Explained, 6: Thaumiel)
//Reported Date: Date
//Description: Text
//Image: img
//Reporter ID: reporterID
//Reporter Name: reporterName


