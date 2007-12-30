/* Tabelle Geb‰ude */

INSERT INTO Person (id, name, vorname, titel)
VALUES (1, 'Doerfert', 'Frank', 'Dr.');

INSERT INTO Person (id, name, vorname, titel)
VALUES (2, 'Mustermann', 'Stefan', 'Prof. Dr.');

/* Tabelle Geb‰ude */

INSERT INTO Gebaude (id, gebaeudename, strasse, hausnummer)
VALUES (1, 'AVZ', 'Universit‰tsstraﬂe', 21);

INSERT INTO Gebaude (id, gebaeudename, strasse, hausnummer)
VALUES (2, 'Informatikzentrum', 'musterweg', 78);

INSERT INTO Gebaude (id, gebaeudename, strasse, hausnummer)
VALUES (3, 'FI204', 'Fleyer Straﬂe', 204);

/* Tabelle Raum */

INSERT INTO Raum (id, nummer, gebaeudeID)
VALUES (1, 'U15', 1);

INSERT INTO Raum (id, nummer, gebaeudeID)
VALUES (2, '017', 3);

INSERT INTO Raum (id, nummer, gebaeudeID)
VALUES (3, '14', 1);

INSERT INTO Raum (id, nummer, gebaeudeID)
VALUES (4, '12', 2);

