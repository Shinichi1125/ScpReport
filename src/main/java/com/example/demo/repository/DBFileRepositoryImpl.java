package com.example.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.model.DBFile;

@Repository
public class DBFileRepositoryImpl implements DBFileRepository {
	
	private final JdbcTemplate jdbcTemplate; 
	
	@Autowired
	public DBFileRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate; 
	}
	
	@Override
	public DBFile storeFile(MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DBFile getFile(String fileId) {
		// TODO Auto-generated method stub
		return null;
	}

}
