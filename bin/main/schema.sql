CREATE TABLE report (
  report_id int NOT NULL AUTO_INCREMENT,
  title varchar(50) NOT NULL,
  threat_level int(1) NOT NULL,
  report_date datetime NOT NULL, 
  description text,
  user_id int NOT NULL,
  img_path varchar(255) DEFAULT '',
  file_name varchar(30),
  file_type varchar(30),
  image blob,
  PRIMARY KEY(report_id)
);

CREATE TABLE user (
  user_id int NOT NULL,
  user_name varchar(30) NOT NULL,
  PRIMARY KEY(user_id)
);






