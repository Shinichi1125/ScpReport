CREATE TABLE report (
  report_id int(3) NOT NULL AUTO_INCREMENT,
  title varchar(50) NOT NULL,
  threat_level int(1) NOT NULL,
  report_date datetime NOT NULL, 
  description text,
 PRIMARY KEY(report_id)
);





