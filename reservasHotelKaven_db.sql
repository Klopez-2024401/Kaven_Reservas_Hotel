drop database if exists reservasHotelKaven_db;
create database reservasHotelKaven_db;
use reservasHotelKaven_db;

create table Usuario(
	id_usuario int not null auto_increment,
    nombre_usuario varchar(64) not null,
    correo_usuario varchar(128) not null,
    contraseña varchar(128) not null,
    constraint pk_usuario primary key (id_usuario)
);

