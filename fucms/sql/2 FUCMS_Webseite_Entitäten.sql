DROP TABLE Version;
DROP TABLE Version_Inhalt;
DROP TABLE Medien;
DROP TABLE Inhalt;
DROP TABLE Webseitenvorlage;
DROP TABLE CMSBenutzer;
DROP TABLE Webseite;


/* Tabelle Version */
CREATE TABLE Version (
	id integer not null primary key,
	vaterseiteID integer, 
	path char(64),
	autor integer not null, 
	titel char(128) not null,  
	gueltig_ab date, 
	gueltig_bis date, 
	charset char(16), 
	sprache char(2),
	hauptseiteninhaltID integer,
	seitenleisteInhaltID integer,
	format integer not null,
	statusID integer not null
);


/* Tabelle Version_Inhalt */
CREATE TABLE Version_Inhalt (
	versionID integer not null, 
	inhaltID integer not null,
	position integer not null, 
	FOREIGN KEY (versionID) REFERENCES Version(id) ON DELETE CASCADE,
	FOREIGN KEY (inhaltID) REFERENCES Inhalt(id) ON DELETE CASCADE
);


/* Tabelle Webseite */
CREATE TABLE Webseite (
	id integer not null primary key, 
	vaterseiteID integer
);

/* Tabelle CMSBenutzer */
CREATE TABLE CMSBenutzer (
	id integer not null primary key, 
	grad char(16), 
	name char(32) not null, 
	vorname char(32), 
	telefon char(32), 
	email char(64), 
	rechte char(16)
);

/* Tabelle Webseitenvorlage */
CREATE TABLE Webseitenvorlage (
	id integer not null primary key, 
	name char(32) not null, 
	dateiname char(32) not null
);


/* Tabelle Inhalt */
CREATE TABLE Inhalt (
	id integer not null primary key, 
	inhaltstyp char(32) not null, 
	inhaltstext varchar2(4000) not null
);


/* Tabelle Medien */
CREATE TABLE Medien (
	id integer not null primary key, 
	name char(32) not null,
	link char(64) not null
);

