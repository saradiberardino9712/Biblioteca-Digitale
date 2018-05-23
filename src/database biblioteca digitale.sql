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
data_nascita date not null,
email varchar (50) unique not null,
titolo_studio varchar (100) not null,
professione varchar (100),
ID_ruolo integer unsigned not null,
constraint utente_ruolo foreign key (ID_ruolo) references ruolo(ID) 
);


create table opera (
ID integer unsigned primary key not null auto_increment,
titolo varchar (50) not null,
anno integer unsigned not null,
autore varchar (50) not null,
constraint opera_unica unique (titolo,anno,autore)
);


create table consulta (
ID integer unsigned primary key not null auto_increment,
ID_utente integer unsigned not null,
ID_opera integer unsigned not null,
constraint consulta_utente foreign key (ID_utente) references utente (ID),
constraint consulta_opera foreign key (ID_opera) references opera(ID)
);


