package com.example.demo.repository;

import com.example.demo.model.DBFile;
import org.springframework.data.jdbc.repository.JdbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBFileRepository extends JdbcRepository<DBFile, String> {

}
