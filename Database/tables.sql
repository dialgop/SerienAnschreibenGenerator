DROP TABLE IF EXISTS Fahrzeug;

CREATE TABLE Fahrzeug (
FahrzeugId int NOT NULL AUTO_INCREMENT,
FahrzeugTyp varchar(20) NOT NULL,
Bezeichnung varchar(30) NOT NULL,
Heersteller varchar(30) NOT NULL,
LeistungKW int NOT NULL,
Verkaufspreis int NOT NULL,
PRIMARY KEY (FahrzeugId) );


DROP TABLE IF EXISTS Kunde;

CREATE TABLE Kunde (
KundeId int NOT NULL AUTO_INCREMENT,
name varchar(30) NOT NULL,
vorname varchar(30) NOT NULL,
anschrift varchar(30) NOT NULL,
postleitzahl int NOT NULL,
stadt varchar(30) NOT NULL,
PRIMARY KEY (KundeId) );


CREATE USER 'automobilhaus'@'localhost' IDENTIFIED BY '[4ut0mobi1Haus]';
GRANT ALL PRIVILEGES ON automobilhaus.* TO 'automobilhaus'@'localhost';

