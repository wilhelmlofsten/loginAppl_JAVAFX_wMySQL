DROP TABLE IF EXISTS Users CASCADE;

CREATE TABLE Users(
	userID INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(30) NULL,    
    password VARCHAR(30) NULL,
    favChef VARCHAR(30) NULL,
	PRIMARY KEY(userID)) ENGINE=InnoDB;

USE login_schema;

INSERT INTO Users (userID,username, password, favChef) VALUES (4,"hello", "password", "cookieMonster");
SELECT * FROM Users;