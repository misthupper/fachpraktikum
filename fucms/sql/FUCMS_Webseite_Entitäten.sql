-- Tabelle Webseite
DROP TABLE Webseite
CREATE TABLE Webseite (integer seitenid not null primary key, integer vaterseite)


-- Tabelle Version
DROP TABLE Version
CREATE TABLE Version (integer versionsid not null primary key, datetime versionsstand not null, integer autor not null, char(128) titel not null, datetime gueltig_ab, datetime gueltig_bis, char(16) charset, char(2) sprache, integer format not null, byte statusid not null, integer seitenid, integer personennr not null)


-- Tabelle Webseitenvorlage
DROP TABLE Webseitenvorlage
CREATE TABLE Webseitenvorlage (integer vorlagenid not null primary key, char(32) name not null, char(32) name not null)


-- Tabelle Inhalt
DROP TABLE Inhalt
CREATE TABLE Inhalt (integer inhaltsid not null primary key, char(32) inhaltstyp not null, text inhaltstext not null)


-- Tabelle Medien
DROP TABLE Medien
CREATE TABLE Medien (integer medienid not null primary key, BLOB medium)


-- Tabelle CMSBenutzer
DROP TABLE CMSBenutzer
CREATE TABLE CMSBenutzer (integer persnr not null primary key, char(16) grad, char(32) name not null, char(32) vorname, char(4) telefon, char(64) email, char(16) rechte)


-- Tabelle Version_Inhalt
DROP TABLE Version_Inhalt
CREATE TABLE Version_Inhalt (integer versionsid not null primary key, integer position not null primary key, integer inhaltsid not null)