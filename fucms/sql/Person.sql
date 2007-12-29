DROP TABLE person;

CREATE TABLE person (
persnr INTEGER NOT NULL PRIMARY KEY ,
name char(32) NOT NULL ,
vorname char(32) ,
titel char(16) ,
telefon char(4) ,
email char(64) 
); 

INSERT INTO person
(persnr, name, vorname, titel, telefon, email)
VALUES (
5 ,
'Mustermann' ,
'Otto' ,
'Prof.', 
'110' ,
'om@fun.de'
);

INSERT INTO person
(persnr, name, vorname, titel, telefon, email)
VALUES (
6 ,
'Musterfrau' ,
'Ursula' ,
'Prof.', 
'112' ,
'um@fun.de'
);
