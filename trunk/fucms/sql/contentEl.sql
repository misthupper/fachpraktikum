DROP TABLE contentEl;

CREATE TABLE contentEl
(id char(20) PRIMARY KEY,
validFrom DATE,
type char(20) NOT NULL,
content char(20) NOT NULL
);


insert into contentEl (id, validFrom, type, content)
VALUES ('aaaa1', '01.11.2007', 'contact', 'a0001');

insert into contentEl (id, validFrom, type, content)
VALUES ('aaaa2', '01.11.2007', 'contact', 'b0001');