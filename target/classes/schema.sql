DROP TABLE IF EXISTS USER;
CREATE TABLE USER (
  id INTEGER AUTO_INCREMENT,
  confirmation_token VARCHAR(250) NOT NULL,
  username VARCHAR(250) NOT NULL,
  enabled INTEGER not null,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  lastseen VARCHAR(250),
  gender VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  authority VARCHAR(250) NOT NULL,
  PRIMARY KEY (id)
);

--INSERT INTO USER (id, first_name, last_name, username, password, authority, gender) VALUES
--  (1,'utrains', 'root', 'utrains-root@utrains.com','school1','DOCTOR', 'Male');