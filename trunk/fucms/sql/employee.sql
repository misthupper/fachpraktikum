drop table employee;

CREATE TABLE employee
(id char(20),
description char(200),
email char(50),
location char(100),
adress char(200),
phone1 char(30)
);

insert into employee
(id, description, email, location, adress, phone1)
VALUES ('a0001','Senatsbeauftragter f&uuml;r behinderte und chronisch kranke Studierende',
'senbeauf@fernuni-hagen.de',
'AVZ, Raum U15 (Erdgeschoss)',
'Universit&auml;tsstra&szlig;e 21<br /> 58097 Hagen',
'02331 / 987 - 40 49');

insert into employee
(id, description, email, location, adress, phone1)
VALUES ('b0001','Senatsbeauftragter f&uuml;r behinderte und chronisch kranke Studierende',
'senbeauf@fernuni-hagen.de',
'AVZ, Raum U15 (Erdgeschoss)',
'Universit&auml;tsstra&szlig;e 21<br /> 58097 Hagen',
'02331 / 987 - 40 49');