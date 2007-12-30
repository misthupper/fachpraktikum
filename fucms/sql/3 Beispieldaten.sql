/* Tabelle Person */

INSERT INTO Person (id, name, vorname, titel)
VALUES (1, 'Doerfert', 'Frank', 'Dr.');

INSERT INTO Person (id, name, vorname, titel)
VALUES (2, 'Mustermann', 'Stefan', 'Prof. Dr.');

/* Tabelle Person_Email */

INSERT INTO Person_Email (id, personid, email)
VALUES (1, 1, 'senbeauf@fernuni-hagen.de');

INSERT INTO Person_Email (id, personid, email)
VALUES (2, 1, 'Frank.Doerfert@fernuni-hagen.de');

/* Tabelle Person_Telefon */

INSERT INTO Person_Telefon (id, personid, telefon)
VALUES (1, 1, '02331 / 987 - 40 49');

INSERT INTO Person_Telefon (id, personid, telefon)
VALUES (2, 1, '02331 / 987 - 25 82');

/* Tabelle Gebäude */

INSERT INTO Gebaeude (id, gebaeudename, strasse, hausnummer, postleitzahl, ort)
VALUES (1, 'AVZ', 'Universitätsstraße', 21, 58097, 'Hagen');

INSERT INTO Gebaeude (id, gebaeudename, strasse, hausnummer, postleitzahl, ort)
VALUES (2, 'Informatikzentrum', 'musterweg', 78, 58097, 'Hagen');

INSERT INTO Gebaeude (id, gebaeudename, strasse, hausnummer, postleitzahl, ort)
VALUES (3, 'FI204', 'Fleyer Straße', 204, 58097, 'Hagen');

/* Tabelle Raum */

INSERT INTO Raum (id, nummer, gebaeudeID)
VALUES (1, 'U15', 1);

INSERT INTO Raum (id, nummer, gebaeudeID)
VALUES (2, '017', 3);

/* Tabelle Einrichtung */

INSERT INTO Einrichtung (id, name, typ, obereinrichtung)
VALUES (1, 'Fernuniversität Hagen', 'Universität', NULL);

INSERT INTO Einrichtung (id, name, typ, obereinrichtung)
VALUES (2, 'Senatsbeauftragter für behinderte und chronisch kranke Studierende', 'Beauftragter', 1);

INSERT INTO Einrichtung (id, name, typ, obereinrichtung)
VALUES (3, 'Stabsstelle für Qualitätssicherung und Evaluation', 'Stabsstelle', 1);


/* Tabelle Person_Einrichtung */

INSERT INTO Person_Einrichtung (personID, einrichtungID, funktion)
VALUES (1, 2, 'Senatsbeauftragter für behinderte und chronisch kranke Studierende');

INSERT INTO Person_Einrichtung (personID, einrichtungID, funktion)
VALUES (1, 3, 'Stabsstelle für Qualitätssicherung und Evaluation');

