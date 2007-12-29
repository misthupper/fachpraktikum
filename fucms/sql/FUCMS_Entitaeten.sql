/* Tabelle Gebäude */
DROP TABLE Gebaeude;
CREATE TABLE Gebaeude (
integer id not null PRIMARY KEY,
char(64) strasse not null,
byte hausnummer not null);

/* Tabelle Raum */
DROP TABLE Raum;
CREATE TABLE Raum (
	integer id not null PRIMARY KEY,
	char(4) nummer not null,
	integer gebaeudeID not null,
    FOREIGN KEY (gebaeudeID) REFERENCES Gebaeude(id)
);

/* Tabelle Person */
DROP TABLE Person;
CREATE TABLE Person (
	integer id not null PRIMARY KEY,
	char(32) name not null,
	char(32) vorname,
	char(16) titel,
	PRIMARY KEY (id)
);

INSERT INTO Person (id, , name, vorname, titel)
VALUES ('1', 'Doerfert', 'Frank', 'Dr.');


/* Tabelle Person_Email */
DROP TABLE Person_Email;
CREATE TABLE Person_Email (
	integer #*id,
	integer personID,
	char(64) email,
    FOREIGN KEY (personID) REFERENCES Person(id)
);

INSERT INTO Person_Email (id, personid, email)
VALUES ('1', '1', 'senbeauf@fernuni-hagen.de');

INSERT INTO Person_Email (id, personid, email)
VALUES ('2', '1', 'Frank.Doerfert@fernuni-hagen.de');

/* Tabelle Person_Telefon */
DROP TABLE Person_Telefon;
CREATE TABLE Person_Telefon (
	integer #*id,
	integer personID,
	char(32) telefon,
	FOREIGN KEY (personID) REFERENCES Person(id)
);

INSERT INTO Person_Telefon (id, personid, email)
VALUES ('1', '1', '02331 / 987 - 40 49');

INSERT INTO Person_Telefon (id, personid, email)
VALUES ('2', '1', '02331 / 987 - 25 82');

/* Tabelle Person_Raum */
DROP TABLE Person_Raum;
CREATE TABLE Person_Raum (
	integer persID not null,
	integer raumID not null,
	FOREIGN KEY (personID) REFERENCES Person(id),
	FOREIGN KEY (raumID) REFERENCES Raum(id)
);

/* Tabelle Einrichtung */
DROP TABLE Einrichtung;
CREATE TABLE Einrichtung (
	integer id not null PRIMARY KEY,
	char(64) name not null,
	char(32) typ not null,
	integer obereinrichtung
);

/* Tabelle Person_Einrichtung */
DROP TABLE Person_Einrichtung;
CREATE TABLE Person_Einrichtung (
	integer personID not null,
	integer einrichtungID not null,
	char(64) funktion,
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
	integer forschungsclusterID not null, 
	integer einrichtungID not null,
	FOREIGN KEY (forschungsclusterID) REFERENCES Forschungscluster(id),
	FOREIGN KEY (einrichtungID) REFERENCES Einrichtung(id)
);

/* Tabelle Abschlussarbeit */
DROP TABLE Abschlussarbeit;
CREATE TABLE Abschlussarbeit (
	integer id not null PRIMARY KEY,
	char(64) verfasser not null,
	char(128) titel not null,
	integer einrichtungID not null,
	FOREIGN KEY (einrichtungID) REFERENCES Einrichtung(id)
);

/* Tabelle Studiengang */

DROP TABLE Studiengang;
CREATE TABLE Studiengang (
	integer id not null PRIMARY KEY,
	char(64) titel not null,
	char(32) art not null,
	integer einrichtungsID not null,
	FOREIGN KEY (einrichtungID) REFERENCES Einrichtung(id)
);

/* Tabelle Kurs */
DROP TABLE Kurs;
CREATE TABLE Kurs (
	integer nummer not null PRIMARY KEY, 
	char(128) titel not null,
	char(32) typ not null
);

-- Tabelle Studiengang_Kurs
DROP TABLE Studiengang_Kurs;
CREATE TABLE Studiengang_Kurs (
	integer studiengangID not null,
	integer kursnummer not null
	FOREIGN KEY (studiengangID) REFERENCES Studiengang(id)
);

/* Tabelle Person_Kurs */
DROP TABLE Person_Kurs
CREATE TABLE Person_Kurs (
	integer personid not null,
	integer kursID not null,
	FOREIGN KEY (personid) REFERENCES Person(id),
	FOREIGN KEY (kursID) REFERENCES Kurs(id)
);

/* Tabelle Kursmodule */
DROP TABLE Kursmodul;
CREATE TABLE Kursmodule (
	integer id not null PRIMARY KEY,
	char(32) bezeichnung not null,
	integer studiengangID,
	FOREIGN KEY (studiengangID) REFERENCES Studiengang(id)
);

/* Tabelle Kursmodule_Kurs */
DROP TABLE Kursmodul_Kurs;
CREATE TABLE Kursmodule_Kurs (
	integer kursID not null, 
	integer modulID not null,
	FOREIGN KEY (kursID) REFERENCES Kurs(id),
	FOREIGN KEY (kursmodulID) REFERENCES Kursmodul(id)
);

