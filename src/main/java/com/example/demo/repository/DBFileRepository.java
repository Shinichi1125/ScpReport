package com.example.demo.repository;

import com.example.demo.model.DBFile;

public interface DBFileRepository {
	DBFile storeFile(DBFile file);
	DBFile getFile(String fileId);
}
