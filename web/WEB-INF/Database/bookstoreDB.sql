DROP DATABASE IF EXISTS bookstoreDB;
CREATE DATABASE bookstoreDB;
USE bookstoreDB;
-- Database: `bookStoreDB`
-- --------------------------------------------------------
-- Table structure for table `Account`
CREATE TABLE `account` (
  `username` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `nome` varchar(128) NOT NULL,
  `cognome` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `tipo` varchar(128) NOT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY (`email`)
);
-- Table structure for table `Indirizzo`
CREATE TABLE `indirizzo` (
  `id` int NOT NULL AUTO_INCREMENT,
  `via` varchar(128) NOT NULL,
  `comune` varchar(128) NOT NULL,
  `provincia` varchar(128) NOT NULL,
  `cap` int NOT NULL,
  `note` text,
  PRIMARY KEY (`id`)
);
-- Table structure for table `Libro`
CREATE TABLE `libro`(
	`ISBN` varchar(14),
	`prezzo` float not null,
	`quantita` int not null,
	`trama` text not null,
	`titolo` varchar(100) not null,
	`copertina` varchar(400) not null unique,
    `disabilitato` float not null,
	FULLTEXT KEY (`titolo`),
	FULLTEXT KEY (`titolo`,`trama`),
	primary key (`ISBN`)
);
-- Table structure for table `Ordine`
CREATE TABLE `ordine` (
  `id` int AUTO_INCREMENT,
  `quantita`double NOT NULL,
  `totale` float NOT NULL,
  `datadiacquisto` time NOT NULL,
  `username` varchar(32) NOT NULL,
  `spedito` boolean default false,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`username`) REFERENCES account(`username`) on update cascade on delete cascade
);
-- Table structure for table `Libro ordinato`
CREATE TABLE `libroordinato` (
  `id` int NOT NULL AUTO_INCREMENT,
  `quantita`double NOT NULL,
  `prezzo` float NOT NULL,
  `ISBN` varchar(14),
  `idordine` int not null,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`ISBN`) REFERENCES libro(`ISBN`) on update cascade on delete cascade,
  FOREIGN KEY (`idordine`) REFERENCES ordine(`id`) on update cascade on delete cascade
);
-- Table structure for table `Autore`
CREATE TABLE `autore` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome`varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
);
-- Table structure for table `Categoria`
CREATE TABLE `categoria` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `descrizione` text NOT NULL,
  PRIMARY KEY (`id`)
);
-- Table structure for relation `Libro` and `Autore`
CREATE TABLE `libroautore`(
	`ISBN` varchar(14),
	`id` int,
    primary key(`id`,`isbn`),
    FOREIGN KEY (`id`) REFERENCES categoria(`id`) on update cascade on delete cascade,
    FOREIGN KEY (`ISBN`) REFERENCES libro(`ISBN`) on update cascade on delete cascade
);
-- Table structure for relation `Libro` and `Categoria`
CREATE TABLE `librocategoria` (
  `ISBN` varchar(14),
  `id` int NOT NULL,
  `nome` varchar(100) NOT NULL,
  `descrizione` text NOT NULL,
  PRIMARY KEY (`ISBN`),
  FOREIGN KEY (`id`) REFERENCES categoria(`id`) on update cascade on delete cascade
);
