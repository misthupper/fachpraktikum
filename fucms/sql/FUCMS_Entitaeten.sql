DROP TABLE Kursmodul_Kurs;
DROP TABLE Person_Kurs;
DROP TABLE Studiengang_Kurs;
DROP TABLE Einrichtung_Forschungscluster;
DROP TABLE Person_Einrichtung;
DROP TABLE Abschlussarbeit;
DROP TABLE Forschungscluster;
DROP TABLE Person_Raum;
DROP TABLE Person_Telefon;
DROP TABLE Person_Email;
DROP TABLE Person;
DROP TABLE Raum;
DROP TABLE Gebaeude;
DROP TABLE Kurs;
DROP TABLE Kursmodul;
DROP TABLE Studiengang;
DROP TABLE Einrichtung;

/* Tabelle Gebäude */
CREATE TABLE Gebaeude (
	id integer not null PRIMARY KEY,
	strasse CHAR(64) not null,
	hausnummer integer not null
);

/* Tabelle Raum */
CREATE TABLE Raum (
	id integer not null PRIMARY KEY,
	nummer char(4) not null,
	gebaeudeID integer not null,
    FOREIGN KEY (gebaeudeID) REFERENCES Gebaeude(id) ON DELETE CASCADE
);

/* Tabelle Person */
CREATE TABLE Person (
	id integer not null PRIMARY KEY,
	name char(32) not null,
	vorname char(32),
	titel char(16)
);

INSERT INTO Person (id, name, vorname, titel)
VALUES (1, 'Doerfert', 'Frank', 'Dr.');

INSERT INTO Person (id, name, vorname, titel)
VALUES (2, 'Mustermann', 'Stefan', 'Prof. Dr.');


/* Tabelle Person_Email */
CREATE TABLE Person_Email (
	id integer not null PRIMARY KEY,
	personID integer,
	email char(64),
    FOREIGN KEY (personID) REFERENCES Person(id)ON DELETE CASCADE
);

INSERT INTO Person_Email (id, personid, email)
VALUES (1, 1, 'senbeauf@fernuni-hagen.de');

INSERT INTO Person_Email (id, personid, email)
VALUES (2, 1, 'Frank.Doerfert@fernuni-hagen.de');

/* Tabelle Person_Telefon */
CREATE TABLE Person_Telefon (
	id integer not null PRIMARY KEY,
	personID integer,
	telefon char(32),
	FOREIGN KEY (personID) REFERENCES Person(id)ON DELETE CASCADE
);

INSERT INTO Person_Telefon (id, personid, telefon)
VALUES (1, 1, '02331 / 987 - 40 49');

INSERT INTO Person_Telefon (id, personid, telefon)
VALUES (2, 1, '02331 / 987 - 25 82');

/* Tabelle Person_Raum */
CREATE TABLE Person_Raum (
	personID integer not null,
	raumID integer not null,
	FOREIGN KEY (personID) REFERENCES Person(id) ON DELETE CASCADE,
	FOREIGN KEY (raumID) REFERENCES Raum(id) ON DELETE CASCADE
);


/* Tabelle Forschungscluster */
CREATE TABLE Forschungscluster (
	id integer  not null PRIMARY KEY,
	name char(64) not null
);

/* Tabelle Abschlussarbeit */
CREATE TABLE Abschlussarbeit (
	id integer not null PRIMARY KEY,
	verfasser char(64) not null,
	titel char(128) not null,
	einrichtungID integer not null,
	FOREIGN KEY (einrichtungID) REFERENCES Einrichtung(id) ON DELETE CASCADE
);

/* Tabelle Einrichtung */
CREATE TABLE Einrichtung (
	id integer not null PRIMARY KEY,
	name char(64) not null,
	typ char(32) not null,
	obereinrichtung integer
);

/* Tabelle Einrichtung_Forschungscluster */
CREATE TABLE Einrichtung_Forschungscluster (
	forschungsclusterID integer not null, 
	einrichtungID integer not null,
	FOREIGN KEY (forschungsclusterID) REFERENCES Forschungscluster(id) ON DELETE CASCADE,
	FOREIGN KEY (einrichtungID) REFERENCES Einrichtung(id) ON DELETE CASCADE
);

/* Tabelle Person_Einrichtung */
CREATE TABLE Person_Einrichtung (
	personID integer not null,
	einrichtungID integer not null,
	funktion char(64),
	FOREIGN KEY (personID) REFERENCES Person(id) ON DELETE CASCADE,
	FOREIGN KEY (einrichtungID) REFERENCES Einrichtung(id) ON DELETE CASCADE
);

/* Tabelle Studiengang */
CREATE TABLE Studiengang (
	id integer not null PRIMARY KEY,
	titel char(64) not null,
	art char(32) not null,
	einrichtungID integer not null,
	FOREIGN KEY (einrichtungID) REFERENCES Einrichtung(id) ON DELETE CASCADE
);

/* Tabelle Kurs */
CREATE TABLE Kurs (
	id integer not null PRIMARY KEY, 
	titel char(128) not null,
	typ char(32) not null
);

/* Tabelle Studiengang_Kurs */
CREATE TABLE Studiengang_Kurs (
	studiengangID integer not null,
	kursID integer not null,
	FOREIGN KEY (studiengangID) REFERENCES Studiengang(id) ON DELETE CASCADE,
	FOREIGN KEY (kursID) REFERENCES Kurs(id) ON DELETE CASCADE
);

/* Tabelle Person_Kurs */
CREATE TABLE Person_Kurs (
	personid integer not null,
	kursID integer not null,
	FOREIGN KEY (personid) REFERENCES Person(id) ON DELETE CASCADE,
	FOREIGN KEY (kursID) REFERENCES Kurs(id) ON DELETE CASCADE
);

/* Tabelle Kursmodule */
CREATE TABLE Kursmodul (
	id integer not null PRIMARY KEY,
	bezeichnung char(32) not null,
	studiengangID integer,
	FOREIGN KEY (studiengangID) REFERENCES Studiengang(id) ON DELETE CASCADE
);

/* Tabelle Kursmodul_Kurs */
CREATE TABLE Kursmodul_Kurs (
	kursID integer not null, 
	kursmodulID integer not null,
	FOREIGN KEY (kursID) REFERENCES Kurs(id) ON DELETE CASCADE,
	FOREIGN KEY (kursmodulID) REFERENCES Kursmodul(id) ON DELETE CASCADE
);
