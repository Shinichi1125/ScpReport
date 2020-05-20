package com.example.demo.entity;

import java.sql.Blob;
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
	//private int fileId;
	private String fileName;
	private String fileType; 
	//private Blob image;
	private byte[] image;
	//private File file; 
	
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
	
//	public int getFileId() {
//		return fileId; 
//	}
//	
//	public void setFileId(int fileId) {
//		this.fileId = fileId; 
//	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public /*Blob*/ byte[] getImage() {
		return image;
	}

	public void setImage(/*Blob*/ byte[] image) {
		this.image = image;
	}
	
//	public File getFile() {
//		return file; 
//	}
//	
//	public void setFile(File file) {
//		this.file = file; 
//	}
//	
}




