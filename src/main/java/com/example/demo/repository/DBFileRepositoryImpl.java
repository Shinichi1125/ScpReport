package com.example.demo.repository;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.DBFile;

@Repository
public class DBFileRepositoryImpl implements DBFileRepository {
	
	private final JdbcTemplate jdbcTemplate; 
	
	@Autowired
	public DBFileRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate; 
	}
	
	@Override
	public DBFile storeFile(DBFile file) {
		DBFile dbFile = new DBFile();
		jdbcTemplate.update("INSERT INTO files(file_id, file_name, file_type, image) VALUES(?, ?, ?, ?)",
				file.getId(), file.getFileName(), file.getFileType(), file.getData());
		dbFile = getFile(file.getId());
		return dbFile;
	}

	@Override
	public DBFile getFile(String fileId) {
		String sql = "SELECT file_id, file_name, file_type, image "
				+ "FROM files "
				+ "WHERE file_id = ?";
		
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, fileId); 
		
		DBFile dbFile = new DBFile(); 
		dbFile.setId((String)result.get("file_id"));
		dbFile.setFileName((String)result.get("file_name"));
		dbFile.setFileType((String)result.get("file_type"));
		dbFile.setData((byte[])result.get("image"));
		
		return dbFile;
	}

}
