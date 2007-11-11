DROP TABLE page;

CREATE TABLE page
(id char(20) PRIMARY KEY,
validFrom DATE,
validTo DATE,
topMenu char(10) ,
author char(10) ,
redakteur char(10) ,
title char(50),
father char(20),
owncss char(20),
child0 char(20),
child1 char(20),
child2 char(20),
child3 char(20),
child4 char(20),
child5 char(20),
child6 char(20),
child7 char(20),
child8 char(20),
child9 char(20),
child10 char(20),
child11 char(20),
child12 char(20),
child13 char(20),
child14 char(20),
child15 char(20),
child16 char(20),
child17 char(20),
child18 char(20),
child19 char(20),
contentEl0 char(20),
contentEl1 char(20),
contentEl2 char(20),
contentEl3 char(20),
contentEl4 char(20),
contentEl5 char(20),
contentEl6 char(20),
contentEl7 char(20),
contentEl8 char(20),
contentEl9 char(20),
contentEl10 char(20),
contentEl11 char(20),
contentEl12 char(20),
contentEl13 char(20),
contentEl14 char(20),
contentEl15 char(20),
contentEl16 char(20),
contentEl17 char(20),
contentEl18 char(20),
contentEl19 char(20)
);

INSERT INTO page (id, validFrom, validto, title, father, owncss, child0)
VALUES ('universitaet', '01.11.2007', '01.11.2008', 'Universitaet', null, 'universitaet', 'verantw');

INSERT INTO page (id, validFrom,validto, title, father, owncss)
VALUES ('studium', '01.11.2007', '01.11.2008', 'Studium', null, 'studium');

INSERT INTO page (id, validFrom,validto, title, father, owncss)
VALUES ('forschung', '01.11.2007','01.11.2008',  'Forschung', null, 'forschung');

INSERT INTO page (id, validFrom,validto, title, father, owncss)
VALUES ('uni intern', '01.11.2007','01.11.2008',  'Uni intern', null, 'uni_intern');

INSERT INTO page (id, validFrom,validto, title, father, owncss, contentEl0, contentEl1)
VALUES ('verantw', '01.11.2007', '01.11.2008', 'Verantwortliche usw...', 'universitaet', null,'aaaa1','aaaa2');

