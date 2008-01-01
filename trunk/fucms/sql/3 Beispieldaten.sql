/* Tabelle CMSBenutzer */
insert into CMSBENUTZER (id, grad, name, vorname, telefon, email, rechte)
values (0, 'dr', 'schmidt', 'hans', '05698-3521458', 'hans.schmidt@fuh.de', '1');

insert into CMSBENUTZER (id, grad, name, vorname, telefon, email, rechte)
values (1, 'prof', 'mueller', 'werner', '05698-3528596', 'mueller.werner@fuh.de', '0');


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
/* Version: 30.12.07, Quelle: http://staffsearch.fernuni-hagen.de/anschrift.php?ca_next_page=1 */

INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (0,'AVZ', 'Allgemeines Verfügungszentrum', 'Universitätsstr. ', '21', '58097', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (1,'ARCADEON', 'Arcadeon', 'Lennestr. ', '91', '58093', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (2,'BRUE10', 'Brüninghausstr. 10', 'Brüninghausstr. ', '10', '58089', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (3,'ESG', 'Eugen-Schmalenbach-Gebäude', 'Universitätsstr. ', '41', '58097', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (4,'F188', 'Feithstr. 188', 'Feithstr. ', '188', '58097', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (5,'FL204', 'Fleyerstr. 204', 'Fleyerstr. ', '204', '58097', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (6,'FL55', 'Fleyerstr. 55', 'Fleyerstr. ', '55', '58097', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (7,'Ha182', 'Haldenerstr. 182', 'Haldenerstr. ', '182', '58095', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (8,'LENNE', 'HÜF', 'Lennestr. ', '89a', '58093', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (9,'DUENN', 'Im Dünningsbruch 9', 'Im Dünningsbruch ', '9', '58095', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (10,'AVZ', 'Allgemeines Verfügungszentrum', 'Universitätsstr. ', '21', '58097', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (11,'ARCADEON', 'Arcadeon', 'Lennestr. ', '91', '58093', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (12,'BRUE10', 'Brüninghausstr. 10', 'Brüninghausstr. ', '10', '58089', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (13,'ESG', 'Eugen-Schmalenbach-Gebäude', 'Universitätsstr. ', '41', '58097', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (14,'F188', 'Feithstr. 188', 'Feithstr. ', '188', '58097', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (15,'FL204', 'Fleyerstr. 204', 'Fleyerstr. ', '204', '58097', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (16,'FL55', 'Fleyerstr. 55', 'Fleyerstr. ', '55', '58097', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (17,'Ha182', 'Haldenerstr. 182', 'Haldenerstr. ', '182', '58095', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (18,'LENNE', 'HÜF', 'Lennestr. ', '89a', '58093', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (19,'DUENN', 'Im Dünningsbruch 9', 'Im Dünningsbruch ', '9', '58095', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (20,'ARNS', 'Studienzentrum Arnsberg', 'Neuer Schulweg ', '11-13', '59821', 'Arnsberg');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (21,'BORK', 'Studienzentrum Borken', 'Josefstr. ', '6', '46325', 'Borken');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (22,'BOTT', 'Studienzentrum Bottrop', 'Blumenstraße 12 ', '- 14', '46236', 'Bottrop');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (23,'BRIL', 'Studienzentrum Brilon', 'Heinrich-Jansen-Weg ', '1', '59929', 'Brilon');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (24,'CAST', 'Studienzentrum Castrop-Rauxel', 'Erinstr. ', '6', '44575', 'Castrop-Rauxel');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (25,'COES', 'Studienzentrum Coesfeld', 'Osterwicker Str. ', '29', '48653', 'Coesfeld');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (26,'ESCHW', 'Studienzentrum Eschweiler', 'Eichendorffstraße ', '14', '52249', 'Eschweiler');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (27,'EUSK', 'Studienzentrum Euskirchen', 'Emil-Fischer-Straße ', '23-27', '53879', 'Euskirchen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (28,'GUMM', 'Studienzentrum Gummersbach', 'Reininghauser Str. ', '32', '51643', 'Gummersbach');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (29,'HAMM', 'Studienzentrum Hamm', 'Stadthausstr. ', '3', '59065', 'Hamm');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (30,'HERF', 'Studienzentrum Herford', 'Münsterkirchplatz ', '1', '32052', 'Herford');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (31,'KREF', 'Studienzentrum Krefeld', 'Petersstraße ', '120', '47798', 'Krefeld');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (32,'LEV', 'Studienzentrum Leverkusen', 'Friedrich-Ebert-Platz ', '11', '51373', 'Leverkusen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (33,'LIPP', 'Studienzentrum Lippstadt', 'Barthstr. ', '2', '59557', 'Lippstadt');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (34,'LUEDEN', 'Studienzentrum Lüdenscheid', 'Liebigstr. ', '11', '58511', 'Lüdenscheid');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (35,'LUEDINGH', 'Studienzentrum Lüdinghausen', 'Amthaus ', '12', '59348', 'Lüdinghausen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (36,'MARL', 'Studienzentrum Marl', 'Bergstr. ', '228', '45768', 'Marl');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (37,'MIND', 'Studienzentrum Minden', 'Alte Kirchstr. ', '9', '32423', 'Minden');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (38,'NEUSS', 'Studienzentrum Neuss', 'Sternstr. ', '62', '41460', 'Neuss');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (39,'OBER', 'Studienzentrum Oberhausen', 'Grevenstr. ', '36', '46045', 'Oberhausen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (40,'RHEI', 'Studienzentrum Rheine', 'Frankenburgstr. ', '5', '48431', 'Rheine');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (41,'WESEL', 'Studienzentrum Wesel', 'Hansaring ', '25', '46483', 'Wesel');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (42,'TGZ', 'Technologie- und Gründerzentrum', 'Universitätsstr. ', '11', '58097', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (43,'UB', 'Universitätsbibliothek', 'Universitätsstr. ', '23', '58097', 'Hagen');
INSERT INTO Gebaeude (id, kuerzel, name, strasse, hausnummer, postleitzahl, ort) VALUES (44,'F152', 'Villa', 'Feithstr. ', '152', '58097', 'Hagen');

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

