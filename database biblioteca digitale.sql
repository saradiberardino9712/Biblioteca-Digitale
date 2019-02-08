drop database if exists bibliotecadigitale;
create database bibliotecadigitale;
use bibliotecadigitale;

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
titolo_studio varchar (100) not null,
professione varchar (100),
email varchar (50) unique not null,
password char (20) not null,
attivo boolean default false,
statodomanda enum('in attesa','accettata','rifiutata'),
livello enum('1','2','3','4','5'),
ID_ruolo integer unsigned not null,
constraint utente_ruolo foreign key (ID_ruolo) references ruolo(ID) on update cascade
);

create table categoria (
ID integer unsigned primary key not null auto_increment,
nome varchar (30)
);

create table opera (
ID integer unsigned primary key not null auto_increment,
titolo varchar (50) not null,
anno varchar(10),
autore varchar (50) not null,
pagine_totali int not null,
statoO enum('in fase di acquisizione','pubblicata') not null,
ID_categoria integer unsigned,
constraint opera_unica unique (titolo,anno,autore),
constraint opera_categoria foreign key (ID_categoria) references categoria (ID) on update cascade on delete set null
);

create table immagine (
ID integer unsigned primary key not null auto_increment,
numero_pagina smallint not null,
statoI enum('in caricamento','in acquisizione','in attesa supervisione','in caricamento acquisizione','in revisione acquisizione','acquisito','eliminata') not null,
url varchar(150) not null,
ID_utente integer unsigned,
ID_opera integer unsigned not null,
constraint immagine_utente foreign key (ID_utente) references utente (ID) on update cascade on delete set null,
constraint immagine_opera foreign key (ID_Opera) references opera(ID) on update cascade on delete cascade
);

create table testo_digitale (
ID integer unsigned primary key not null auto_increment,
testo varchar (50000),
statoT enum('in trascrizione','in attesa revisione','in revisione','trascritto','pubblicato'),
ID_utente integer unsigned,
ID_immagine integer unsigned not null,
constraint testo_digitale_utente foreign key (ID_utente) references utente (ID) on update cascade on delete set null,
constraint testo_digitale_imamgine  foreign key (ID_immagine) references immagine (ID) on delete cascade on update cascade
);

create table notifica(
ID integer unsigned primary key not null auto_increment,
orario timestamp not null,
descrizione varchar(450),
vista boolean default false,
IDutentenot integer unsigned not null,
IDruolonot integer unsigned not null,
ID_utente integer unsigned,
IDimmagine integer unsigned,
constraint notifica_utente foreign key (ID_utente) references utente(ID) on update cascade on delete set null
);
