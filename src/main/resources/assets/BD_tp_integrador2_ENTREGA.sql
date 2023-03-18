create database tp_integrador2;
use tp_integrador2;

-- ************************
-- ** CREACION DE TABLAS **
-- ************************

create table usuarios (
  id_usuario int not null auto_increment,
  usuario varchar(30) not null unique,
  contrasenia varchar(30) not null,
  email varchar(50) not null,
  primary key (id_usuario)
); 

create table plataformas (
  id_plataforma int not null auto_increment,
  plataforma varchar(20),
  url varchar(255),
  primary key (id_plataforma)
); 

create table generos (
  id_genero int not null auto_increment,
  genero varchar(20) unique,
  primary key (id_genero)
); 

create table series (
  id_serie int not null unique,
  titulo varchar(50),
  temporadas int,
  episodios int,
  img_small varchar(255),
  img_big varchar(255),
  anio_lanzamiento varchar(4),
  sitio_oficial varchar(255),
  descripcion varchar(255),
  primary key (id_serie)
);

create table series_generos (
  id_serie int not null,
  id_genero int not null,
  foreign key (id_genero) references generos (id_genero),
  foreign key (id_serie) references series (id_serie)
);

create table usuarios_series (
  id_usuario_serie int not null auto_increment,
  id_usuario int not null,
  id_serie int not null,  
  temp_actual int,
  episod_actual int,
  id_plataforma int not null,
  activa boolean,
  primary key (id_usuario_serie),
  foreign key (id_usuario) references usuarios (id_usuario),
  foreign key (id_serie) references series (id_serie),
  foreign key (id_plataforma) references plataformas (id_plataforma)
);

-- ***********************************************************************
-- **  CARGA DE TABLA GENERO (cargarla antes de iniciar la aplicacion)  **
-- ***********************************************************************

insert into plataformas (plataforma, url) values ('Sin Plataforma', '');
insert into plataformas (plataforma, url) values ('cuevana', 'https://www4.cuevana3.ch/');
insert into plataformas (plataforma, url) values ('pelispedia', 'https://pelispedia.one/');
insert into plataformas (plataforma, url) values ('seriesw', 'https://mindandfist.com/');
insert into plataformas (plataforma, url) values ('plutoTV', 'https://pluto.tv/');