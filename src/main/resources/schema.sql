CREATE TABLE report (
  report_id int(3) NOT NULL AUTO_INCREMENT,
  title varchar(50) NOT NULL,
  threat_level int(1) NOT NULL,
  report_date datetime NOT NULL, 
  description text,
  user_id int(3) NOT NULL,
  img_path varchar(255) DEFAULT '',
  PRIMARY KEY(report_id)
);

CREATE TABLE user (
  user_id int(3) NOT NULL,
  user_name varchar(30) NOT NULL,
  PRIMARY KEY(user_id)
);

CREATE TABLE files (
  file_id int(3) NOT NULL AUTO_INCREMENT,
  file_name varchar(30) NOT NULL,
  file_type varchar(30) NOT NULL,
  image blob,
  PRIMARY KEY(file_id)
);





