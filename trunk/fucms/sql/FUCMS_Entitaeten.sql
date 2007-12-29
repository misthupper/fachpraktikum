/* Tabelle Gebäude */
DROP TABLE Gebaeude;
CREATE TABLE Gebaeude (
	id integer not null PRIMARY KEY,
	strasse CHAR(64) not null,
	hausnummer integer not null
);

/* Tabelle Raum */
DROP TABLE Raum;
CREATE TABLE Raum (
	id integer not null PRIMARY KEY,
	nummer char(4) not null,
	gebaeudeID integer not null,
    FOREIGN KEY (gebaeudeID) REFERENCES Gebaeude(id)
);

/* Tabelle Person */
DROP TABLE Person;
CREATE TABLE Person (
	id integer not null PRIMARY KEY,
	name char(32) not null,
	vorname char(32),
	titel char(16),
	PRIMARY KEY (id)
);

INSERT INTO Person (id, , name, vorname, titel)
VALUES ('1', 'Doerfert', 'Frank', 'Dr.');


/* Tabelle Person_Email */
DROP TABLE Person_Email;
CREATE TABLE Person_Email (
	#*id integer,
	personID integer,
	email char(64),
    FOREIGN KEY (personID) REFERENCES Person(id)
);

INSERT INTO Person_Email (id, personid, email)
VALUES ('1', '1', 'senbeauf@fernuni-hagen.de');

INSERT INTO Person_Email (id, personid, email)
VALUES ('2', '1', 'Frank.Doerfert@fernuni-hagen.de');

/* Tabelle Person_Telefon */
DROP TABLE Person_Telefon;
CREATE TABLE Person_Telefon (
	#*id integer,
	personID integer,
	telefon char(32),
	FOREIGN KEY (personID) REFERENCES Person(id)
);

INSERT INTO Person_Telefon (id, personid, email)
VALUES ('1', '1', '02331 / 987 - 40 49');

INSERT INTO Person_Telefon (id, personid, email)
VALUES ('2', '1', '02331 / 987 - 25 82');

/* Tabelle Person_Raum */
DROP TABLE Person_Raum;
CREATE TABLE Person_Raum (
	personID integer not null,
	raumID integer not null,
	FOREIGN KEY (personID) REFERENCES Person(id),
	FOREIGN KEY (raumID) REFERENCES Raum(id)
);

/* Tabelle Einrichtung */
DROP TABLE Einrichtung;
CREATE TABLE Einrichtung (
	id integer not null PRIMARY KEY,
	name char(64) not null,
	typ char(32) not null,
	obereinrichtung integer
);


/* Tabelle Person_Einrichtung */
DROP TABLE Person_Einrichtung;
CREATE TABLE Person_Einrichtung (
	personID integer not null,
	einrichtungID integer not null,
	funktion char(64),
	FOREIGN KEY (personID) REFERENCES Person(id),
	FOREIGN KEY (einrichtungID) REFERENCES Einrichtung(id)
);

/* Tabelle Forschungscluster */
DROP TABLE Forschungscluster;
CREATE TABLE Forschungscluster (
	integer id not null PRIMARY KEY,
	char(64) name not null
);

/* Tabelle Einrichtung_Forschungscluster */
DROP TABLE Einrichtung_Forschungscluster;
CREATE TABLE Einrichtung_Forschungscluster (
	forschungsclusterID integer not null, 
	einrichtungID integer not null,
	FOREIGN KEY (forschungsclusterID) REFERENCES Forschungscluster(id),
	FOREIGN KEY (einrichtungID) REFERENCES Einrichtung(id)
);

/* Tabelle Abschlussarbeit */
DROP TABLE Abschlussarbeit;
CREATE TABLE Abschlussarbeit (
	id integer not null PRIMARY KEY,
	verfasser char(64) not null,
	titel char(128) not null,
	einrichtungID integer not null,
	FOREIGN KEY (einrichtungID) REFERENCES Einrichtung(id)
);

/* Tabelle Studiengang */

DROP TABLE Studiengang;
CREATE TABLE Studiengang (
	id integer not null PRIMARY KEY,
	titel char(64) not null,
	art char(32) not null,
	einrichtungsID integer not null,
	FOREIGN KEY (einrichtungID) REFERENCES Einrichtung(id)
);

/* Tabelle Kurs */
DROP TABLE Kurs;
CREATE TABLE Kurs (
	nummer integer not null PRIMARY KEY, 
	titel char(128) not null,
	typ char(32) not null
);

-- Tabelle Studiengang_Kurs
DROP TABLE Studiengang_Kurs;
CREATE TABLE Studiengang_Kurs (
	studiengangID integer not null,
	kursnummer integer not null
	FOREIGN KEY (studiengangID) REFERENCES Studiengang(id)
);

/* Tabelle Person_Kurs */
DROP TABLE Person_Kurs
CREATE TABLE Person_Kurs (
	personid integer not null,
	kursID integer not null,
	FOREIGN KEY (personid) REFERENCES Person(id),
	FOREIGN KEY (kursID) REFERENCES Kurs(id)
);

/* Tabelle Kursmodule */
DROP TABLE Kursmodul;
CREATE TABLE Kursmodule (
	integer id not null PRIMARY KEY,
	bezeichnung char(32) not null,
	studiengangID integer,
	FOREIGN KEY (studiengangID) REFERENCES Studiengang(id)
);

/* Tabelle Kursmodule_Kurs */
DROP TABLE Kursmodul_Kurs;
CREATE TABLE Kursmodule_Kurs (
	kursID integer not null, 
	modulID integer not null,
	FOREIGN KEY (kursID) REFERENCES Kurs(id),
	FOREIGN KEY (kursmodulID) REFERENCES Kursmodul(id)
);

