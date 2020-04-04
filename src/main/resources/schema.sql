CREATE TABLE report (
  report_id int(3) NOT NULL AUTO_INCREMENT,
  title varchar(50) NOT NULL,
  threat_level int(1) NOT NULL,
  report_date datetime NOT NULL, 
  description text,
  user_id int(3) NOT NULL,
  PRIMARY KEY(report_id)
);

CREATE TABLE user (
  user_id int(3) NOT NULL,
  user_name varchar(30) NOT NULL,
  PRIMARY KEY(user_id)
);





