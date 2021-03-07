INSERT INTO Account(username,password,nome,cognome,email,tipo,abilitato) VALUES('cliente1','password','Mario','Rossi','email@email.com','C',1);
INSERT INTO Account(username,password,nome,cognome,email,tipo,abilitato) VALUES('manager','password','Giovanni','Rossi','email@email.com','M',1);
INSERT INTO Account(username,password,nome,cognome,email,tipo,abilitato) VALUES('responsabile','password','Fabio','Rossi','email@email.com','R',1);

INSERT INTO Indirizzo(via,comune,provincia,cap,note,username) VALUES('via corsaro 21','Roccabascerana','Avellino',83016,'Da consegnare al piano 15','cliente1');

INSERT INTO Libro(isbn,prezzo,quantita,trama,titolo,copertina,disabilitato,datapubblicazione) VALUES('1134567841',134.1,100,'La trama del libro1','Titolo1','img1.jpg',0,'2008-11-11');
INSERT INTO Libro(isbn,prezzo,quantita,trama,titolo,copertina,disabilitato,datapubblicazione) VALUES('1234567841',12,20,'La trama del libro2','Titolo2','img2.jpg',0,'2008-11-11');
INSERT INTO Libro(isbn,prezzo,quantita,trama,titolo,copertina,disabilitato,datapubblicazione) VALUES('1334567841',15.1,60,'La trama del libro3','Titolo3','img3.jpg',0,'2008-11-11');

INSERT INTO Autore(id,nomecompleto) VALUES(1,"Autore 1");
INSERT INTO Autore(id,nomecompleto) VALUES(2,"Autore 1");
INSERT INTO Autore(id,nomecompleto) VALUES(3,"Autore 1");

INSERT INTO Categoria(id,nome,descrizione) VALUES(1,'Categoria1','Descrizione della categoria1');
INSERT INTO Categoria(id,nome,descrizione) VALUES(2,'Categoria2','Descrizione della categoria2');
INSERT INTO Categoria(id,nome,descrizione) VALUES(3,'Categoria3','Descrizione della categoria3');

Insert into librocategoria(isbn,id) values ('1134567841',1);
Insert into librocategoria(isbn,id) values ('1234567841',1);
Insert into librocategoria(isbn,id) values ('1234567841',2);
Insert into librocategoria(isbn,id) values ('1334567841',3);

INSERT INTO LibroAutore(id,isbn) VALUES(1,'1134567841');
INSERT INTO LibroAutore(id,isbn) VALUES(2,'1234567841');
INSERT INTO LibroAutore(id,isbn) VALUES(3,'1334567841');

