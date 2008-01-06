/* Enthält alle Entitäten */
/* Muss jeweils entsprechend dem Datenbankschema aktualisiert werden */
DROP TABLE FUCMS_Entities;
CREATE TABLE FUCMS_Entities (
	id integer not null PRIMARY KEY,
	name CHAR(64) not null
);

/* Enthält alle Relationen */
/* Muss jeweils entsprechend dem Datenbankschema aktualisiert werden */
DROP TABLE FUCMS_Relations;
CREATE TABLE FUCMS_Relations (
	id integer not null PRIMARY KEY,
	name CHAR(64) not null
);

INSERT INTO FUCMS_Entities (id, name) VALUES (1, 'Einrichtung');
INSERT INTO FUCMS_Entities (id, name) VALUES (2, 'Studiengang');
INSERT INTO FUCMS_Entities (id, name) VALUES (3, 'Kursmodul');
INSERT INTO FUCMS_Entities (id, name) VALUES (4, 'Kurs');
INSERT INTO FUCMS_Entities (id, name) VALUES (5, 'Gebaeude');
INSERT INTO FUCMS_Entities (id, name) VALUES (6, 'Raum');
INSERT INTO FUCMS_Entities (id, name) VALUES (7, 'Person');
INSERT INTO FUCMS_Entities (id, name) VALUES (8, 'Forschungscluster');
INSERT INTO FUCMS_Entities (id, name) VALUES (9, 'Abschlussarbeit');

INSERT INTO FUCMS_Relations (id, name)VALUES (10, 'Kursmodul_Kurs');
INSERT INTO FUCMS_Relations (id, name)VALUES (11, 'Person_Kurs');
INSERT INTO FUCMS_Relations (id, name)VALUES (12, 'Studiengang_Kurs');
INSERT INTO FUCMS_Relations (id, name)VALUES (13, 'Einrichtung_Forschungscluster');
INSERT INTO FUCMS_Relations (id, name)VALUES (14, 'Person_Einrichtung');
INSERT INTO FUCMS_Relations (id, name)VALUES (15, 'Person_Raum');
INSERT INTO FUCMS_Relations (id, name)VALUES (16, 'Person_Telefon');
INSERT INTO FUCMS_Relations (id, name)VALUES (17, 'Person_Email');

/* View zum extrahieren der Foreign Key-Beziehungen */
/* ORACLE-spezifisch */
drop view FUCMS_Foreign_Keys;
create view FUCMS_Foreign_Keys as 
 select c.table_name "Table", d.column_name "Column",
    (select r.table_name 
     from sys.all_constraints r
     where c.r_owner = r.owner
     and c.r_constraint_name = r.constraint_name) "FK_Table",
     (select i.column_name
     from sys.all_constraints s, sys.all_ind_columns i
     where i.index_name = s.index_name
     and c.r_owner = s.owner
     and c.r_constraint_name = s.constraint_name) "FK_ID"
 from sys.all_constraints c,
     ( select a.owner,
       a.table_name,
       a.constraint_name,
       b.column_name
       from sys.all_constraints a,
       sys.all_cons_columns b,
       (select * from fucms_relations union select * from fucms_entities) f
       where a.owner = user
           and a.constraint_name = b.constraint_name
           and a.owner = b.owner
           and a.constraint_type = 'R'
           and trim(upper(a.table_name)) = trim(upper(f.name)) ) d
 where c.owner = d.owner
    and c.table_name      = d.table_name
    and c.constraint_name = d.constraint_name
 order by c.table_name;