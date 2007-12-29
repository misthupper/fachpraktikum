DROP TABLE Version_Inhalt, CMSBenutzer, Medien, Inhalt, Webseitenvorlage, Version, Webseite;


/* Tabelle Webseite */
CREATE TABLE Webseite (
	id integer not null primary key, 
	vaterseiteID integer
);


/* Tabelle Version */
CREATE TABLE Version (
	id integer not null primary key, 
	versionsstand datetime not null, 
	autor integer not null, 
	titel char(128) not null, 
	gueltig_ab datetime, 
	gueltig_bis datetime, 
	charset char(16), 
	sprache char(2), 
	format integer not null, 
	statusIDbyte not null, 
	seitenID integer, 
	autor integer not null,
	FOREIGN KEY (autor) REFERENCES CMSBenutzer(id),
	FOREIGN KEY (format) REFERENCES Webseitenvorlage(id)
);


/* Tabelle Webseitenvorlage */
CREATE TABLE Webseitenvorlage (
	id integer not null primary key, 
	name char(32) not null, 
	name char(32) not null
);


/* Tabelle Inhalt */
CREATE TABLE Inhalt (
	id integer not null primary key, 
	inhaltstyp char(32) not null, 
	inhaltstext text not null
);


/* Tabelle Medien */
CREATE TABLE Medien (
	id integer not null primary key, 
	medium BLOB
);


/* Tabelle CMSBenutzer */
CREATE TABLE CMSBenutzer (
	id integer not null primary key, 
	grad char(16), 
	name char(32) not null, 
	vorname char(32), 
	telefon char(4), 
	email char(64), 
	rechte char(16)
);


/* Tabelle Version_Inhalt */
CREATE TABLE Version_Inhalt (
	id integer not null primary key, 
	position integer not null primary key, 
	inhaltsID integer not null,
	FOREIGN KEY (inhaltsID) REFERENCES Inhalt(id)
);