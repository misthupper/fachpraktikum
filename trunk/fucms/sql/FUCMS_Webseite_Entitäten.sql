-- Tabelle Webseite
DROP TABLE Webseite;
CREATE TABLE Webseite (
	integer id not null primary key, 
	integer vaterseite,
	FOREIGN KEY (vaterseite) REFERENCES Webseite(id)
);


-- Tabelle Version
DROP TABLE Version;
CREATE TABLE Version (
	integer id not null primary key, 
	datetime versionsstand not null, 
	integer autor not null, 
	char(128) titel not null, 
	datetime gueltig_ab, 
	datetime gueltig_bis, 
	char(16) charset, 
	char(2) sprache, 
	integer format not null, 
	byte statusid not null, 
	integer seitenid, 
	integer autor not null,
	FOREIGN KEY (autor) REFERENCES CMSBenutzer(id),
	FOREIGN KEY (format) REFERENCES Webseitenvorlage(id)
);


-- Tabelle Webseitenvorlage
DROP TABLE Webseitenvorlage;
CREATE TABLE Webseitenvorlage (
	integer id not null primary key, 
	char(32) name not null, 
	char(32) name not null
);


-- Tabelle Inhalt
DROP TABLE Inhalt;
CREATE TABLE Inhalt (
	integer id not null primary key, 
	char(32) inhaltstyp not null, 
	text inhaltstext not null
);


-- Tabelle Medien
DROP TABLE Medien;
CREATE TABLE Medien (
	integer id not null primary key, 
	BLOB medium
);


-- Tabelle CMSBenutzer
DROP TABLE CMSBenutzer;
CREATE TABLE CMSBenutzer (
	integer id not null primary key, 
	char(16) grad, 
	char(32) name not null, 
	char(32) vorname, 
	char(4) telefon, 
	char(64) email, 
	char(16) rechte
);


-- Tabelle Version_Inhalt
DROP TABLE Version_Inhalt;
CREATE TABLE Version_Inhalt (
	integer id not null primary key, 
	integer position not null primary key, 
	integer inhaltsid not null,
	FOREIGN KEY (inhaltsid) REFERENCES Inhalt(id)
);