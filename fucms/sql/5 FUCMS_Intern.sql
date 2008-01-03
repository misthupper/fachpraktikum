/* Enthält alle Entitäten */
/* Muss jeweils entsprechend dem Datenbankschema aktualisiert werden */
DROP TABLE FUCMS_Entities;
CREATE TABLE FUCMS_Entities (
	id integer not null PRIMARY KEY,
	name CHAR(64) not null
);

/* Enthält alle Relationen */
/* Muss jeweils entsprechend dem Datenbankschema aktualisiert werden */
DROP TABLE FUCMS_Relations;
CREATE TABLE FUCMS_Relations (
	id integer not null PRIMARY KEY,
	name CHAR(64) not null
);

INSERT INTO FUCMS_Entities (id, name) VALUES (1, 'Einrichtung');
INSERT INTO FUCMS_Entities (id, name) VALUES (2, 'Studiengang');
INSERT INTO FUCMS_Entities (id, name) VALUES (3, 'Kursmodul');
INSERT INTO FUCMS_Entities (id, name) VALUES (4, 'Kurs');
INSERT INTO FUCMS_Entities (id, name) VALUES (5, 'Gebaeude');
INSERT INTO FUCMS_Entities (id, name) VALUES (6, 'Raum');
INSERT INTO FUCMS_Entities (id, name) VALUES (7, 'Person');
INSERT INTO FUCMS_Entities (id, name) VALUES (8, 'Forschungscluster');
INSERT INTO FUCMS_Entities (id, name) VALUES (9, 'Abschlussarbeit');

INSERT INTO FUCMS_Relations (id, name)VALUES (10, 'Kursmodul_Kurs');
INSERT INTO FUCMS_Relations (id, name)VALUES (11, 'Person_Kurs');
INSERT INTO FUCMS_Relations (id, name)VALUES (12, 'Studiengang_Kurs');
INSERT INTO FUCMS_Relations (id, name)VALUES (13, 'Einrichtung_Forschungscluster');
INSERT INTO FUCMS_Relations (id, name)VALUES (14, 'Person_Einrichtung');
INSERT INTO FUCMS_Relations (id, name)VALUES (15, 'Person_Raum');
INSERT INTO FUCMS_Relations (id, name)VALUES (16, 'Person_Telefon');
INSERT INTO FUCMS_Relations (id, name)VALUES (17, 'Person_Email');


