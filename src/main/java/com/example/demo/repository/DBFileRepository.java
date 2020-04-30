package com.example.demo.repository;

import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.DBFile;

public interface DBFileRepository {
	DBFile storeFile(MultipartFile file);
	DBFile getFile(String fileId);
}
