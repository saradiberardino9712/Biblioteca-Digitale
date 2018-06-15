drop database Biblioteca_digitale;
create database Biblioteca_digitale;
use Biblioteca_digitale;

create table ruolo (
 ID integer unsigned primary key not null auto_increment,
 nome_ruolo varchar (50) not null
 );



create table utente (
ID integer unsigned primary key not null auto_increment,
nome varchar (20) not null,
cognome varchar (20) not null,
indirizzo varchar (100),
password char (20),
data_nascita date not null,
email varchar (50) unique not null,
titolo_studio varchar (100) not null,
professione varchar (100),
ID_ruolo integer unsigned not null,
constraint utente_ruolo foreign key (ID_ruolo) references ruolo(ID) on update cascade on delete cascade,
constraint utente_unico unique (email, password)
);

create table categoria (
ID integer unsigned primary key not null auto_increment,
nome varchar (30)
);


create table opera (
ID integer unsigned primary key not null auto_increment,
ID_categoria integer unsigned,
titolo varchar (50) not null,
anno integer unsigned not null,
autore varchar (50) not null,
constraint opera_unica unique (titolo,anno,autore),
constraint opera_categoria foreign key (ID_categoria) references categoria (ID) on update cascade on delete cascade
);


create table consulta (
ID integer unsigned primary key not null auto_increment,
ID_utente integer unsigned not null,
ID_opera integer unsigned not null,
constraint consulta_utente foreign key (ID_utente) references utente (ID) on update cascade on delete cascade,
constraint consulta_opera foreign key (ID_opera) references opera(ID)
);


create table immagine (
ID integer unsigned primary key not null auto_increment,
numero_pagina smallint,
ID_utente integer unsigned not null,
constraint immagine_utente foreign key (ID_utente) references utente (ID)
);

create table trascrive (
ID_utente integer unsigned,
ID_immagine integer unsigned,
constraint trascrive_utente foreign key (ID_utente) references utente(ID),
constraint trascrive_immagine foreign key (ID_immagine) references immagine(ID)
);

create table testo_digitale (
ID integer unsigned primary key not null auto_increment,
ID_utente integer unsigned not null,
ID_immagine integer unsigned not null,
testo varchar (50000),
constraint testo_digitale_utente foreign key (ID_utente) references utente (ID),
constraint testo_digitale_imamgine  foreign key (ID_immagine) references immagine (ID)
);











