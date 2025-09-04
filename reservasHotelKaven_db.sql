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

create table Huesped (
	id_huesped int not null auto_increment,
    nombre_huesped varchar(64) not null,
    apellido_huesped varchar(64) not null,
    correo_huesped varchar(128),
    constraint pk_huesped primary key (id_huesped)
);

create table Habitacion(
	id_habitacion int not null auto_increment,
    numero_habitacion int not null,
    tipo_habitacion ENUM('Simple', 'Doble', 'Familiar', 'Suite') default 'Simple',
    disponibilidad ENUM('Disponible', 'Ocupado') default 'Disponible',
    precio decimal(10,2) not null,
    constraint pk_habitacion primary key (id_habitacion)
);
