drop database if exists ALUME;
create database ALUME;
use ALUME;

create table client (
	idclient int (5) not null auto_increment, 
	nom varchar(50), 
	ville varchar(100),
	codepostal char(5),
	rue varchar(50),
	numrue int(3), 
	email varchar(50)unique, 
	tel varchar(20), 
	constraint pk_cli primary key (idclient)
);
 
create table particulier (
	idclient int (5) not null auto_increment,
	prenom varchar(50),   
	constraint pk_p primary key (idclient)
	);
 
create table entreprise (
	identreprise int(5) not null auto_increment,
	statut varchar(50), 
	numsiret int(14) not null,
	nomrepresentant varchar(50), 
	constraint pk_ent primary key (identreprise)
);
 
create table devis (
	iddevis int(5) not null,
	datedevis date,
	etatdevis enum("acceptee", "annulee"),
	idclient int(5) not null,
	constraint pk_devis primary key (iddevis),
	constraint fk_cli foreign key (idclient) references client(idclient)
);

create table commande(
	idcommande int(10) not null,
	etatcom enum("en attente", "annulee", "livree", "en preparation", "confirmee"),
	codedevis int(5) not null,
	constraint pk_com primary key (idcommande),
	constraint fk_com foreign key (codedevis) references devis(codedevis)
);

create table cat_produit(
	codecat int(4) not null,
	nomcat varchar(20),
	constraint pk_cat primary key (codecat)
);

create table produit(
	idproduit int(6) not null,
	nomproduit varchar(50),
	prix_unit decimal(8,2),
	codecat int(4) not null,
	constraint pk_produit primary key (idproduit),
	constraint fk_cat foreign key (codecat) references cat_produit (codecat)
);

create table ligne_com(
	idproduit int(6) not null,
	codecom int(10) not null,
	quantite int default 0,
	constraint pk_lcom primary key (idproduit, codecom),
	constraint fk_prod foreign key (idproduit) references produit (idproduit),
	constraint fk_lcomm foreign key (codecom) references commande (codecom)
);

create table technicien(
	idtechnicien int(5) not null auto_increment,
	nom varchar(50), 
	prenom varchar(50), 
	specialite enum ("Services", "Ateliers", "Autres"), 
	email varchar(50) unique, 
	mdp varchar (50),
	constraint pk_tech primary key (idtechnicien)
);

create table intervention(
	idtechnicien int(5) not null,
	codecom int(10) not null, 
	datehd datetime not null, 
	datehf datetime ,
	etat enum("en attente", "terminee", "annulee"),
	constraint pk_inter primary key (idtechnicien, codecom,datehd),
	constraint fk_tech foreign key (idtechnicien) references technicien(idtechnicien),
	constraint fk_com_inter foreign key (codecom) references commande(codecom)
);