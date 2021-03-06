package com.example.demo.repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Report;
import com.example.demo.entity.User;

@Repository
public class ReportDaoImpl implements ReportDao {
	
	private final JdbcTemplate jdbcTemplate; 
	
	@Autowired
	public ReportDaoImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate; 
	}

	@Override
	public List<Report> findAll() {
		String sql = "SELECT report_id, title, threat_level, report_date, description, image, "  // used to be just img_path
							+ "user.user_id, user_name FROM report "
							+ "INNER JOIN user ON report.user_id = user.user_id";
		
		List<Map<String, Object>> resultList = jdbcTemplate.queryForList(sql); 
		
		List<Report> list = new ArrayList<Report>(); 
		
		for(Map<String, Object> result: resultList) {
			Report report = new Report(); 
			report.setReportId((int)result.get("report_id"));
			report.setTitle((String)result.get("title"));
			report.setThreatLevel((int)result.get("threat_level"));
			report.setReportDate(((Timestamp) result.get("report_date")).toLocalDateTime());
			report.setDescription((String)result.get("description"));
			report.setImage((byte[])result.get("image"));
			//report.setImgPath((String)result.get("img_path"));
			
			User user = new User();
			
			report.setUserId((int)result.get("user_id"));

			user.setUserId((int)result.get("user_id"));
			user.setUserName((String)result.get("user_name"));
			
			report.setUser(user);
			
			list.add(report);
		}	
		return list;
	}

	@Override
	public Optional<Report> findById(int id) {
		String sql = "SELECT report_id, title, threat_level, report_date, description, image, "  // used to be just img_path
				+ "user.user_id, user_name FROM report "
				+ "INNER JOIN user ON report.user_id = user.user_id "
				+ "WHERE report_id = ?";
		
		Map<String, Object> result = jdbcTemplate.queryForMap(sql, id); 
		
		Report report = new Report(); 
		report.setReportId((int)result.get("report_id"));
		report.setTitle((String)result.get("title"));
		report.setThreatLevel((int)result.get("threat_level"));
		report.setReportDate(((Timestamp) result.get("report_date")).toLocalDateTime());
		report.setDescription((String)result.get("description"));
		report.setImage((byte[])result.get("image"));
		//report.setImgPath((String)result.get("img_path"));
		
		User user = new User();
		
		report.setUserId((int)result.get("user_id"));

		user.setUserId((int)result.get("user_id"));
		user.setUserName((String)result.get("user_name"));
		
		report.setUser(user);
		
		// wrap the report with Optional
		Optional<Report> reportOpt = Optional.ofNullable(report);
		
		return reportOpt;
	}

	@Override
	public void insert(Report report) {
		jdbcTemplate.update("INSERT INTO report(report_id, title, threat_level, report_date, description, image, user_id) VALUES(?, ?, ?, ?, ?, ?, ?)",
				report.getReportId(), report.getTitle(), report.getThreatLevel(), report.getReportDate(), report.getDescription(), report.getImage(), report.getUser().getUserId());
	}

	@Override
	public int update(Report report) {
		return jdbcTemplate.update("UPDATE report SET title = ?, threat_level = ?, report_date = ?, description = ?, image = ? WHERE report_id = ?",
				report.getTitle(), report.getThreatLevel(), report.getReportDate(), report.getDescription(), report.getImage(), report.getReportId());
	}

	@Override
	public int deleteById(int id) {
		return jdbcTemplate.update("DELETE FROM report WHERE report_id = ?", id);
	}

}
