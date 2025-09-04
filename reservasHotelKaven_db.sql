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


create table Reserva (
	id_reserva int not null auto_increment,
    fecha_entrada date not null,
    fecha_salida date not null,
    id_huesped int not null,
    id_habitacion int not null,
    constraint pk_reserva primary key (id_reserva),
    constraint fk_huesped_reserva foreign key (id_huesped)
		references Huesped (id_huesped),
	constraint fk_habitacion_reserva foreign key (id_habitacion)
		references Habitacion (id_habitacion)
);

create table Pago (
	id_pago int not null auto_increment,
    estado_pago ENUM('Pendiente','Pagado') default 'Pendiente',
    id_reserva int not null,
    id_usuario int not null,
    constraint pk_pago primary key (id_pago),
    constraint fk_reserva_pago foreign key (id_reserva)
		references Reserva (id_reserva),
	constraint fk_usuario_pago foreign key (id_usuario)
		references Usuario (id_usuario)
);


insert into Usuario (nombre_usuario, correo_usuario, contraseña)
values 
    ('admin', 'admin@hotelkaven.com', 'password123'),
    ('johndoe', 'johndoe@correo.com', 'qwerty123'),
    ('maryjane', 'maryjane@correo.com', 'abc12345');

insert into Huesped (nombre_huesped, apellido_huesped, correo_huesped)
values
    ('Juan', 'Pérez', 'juan.perez@correo.com'),
    ('María', 'González', 'maria.gonzalez@correo.com'),
    ('Carlos', 'López', 'carlos.lopez@correo.com'),
    ('Lucía', 'Martínez', 'lucia.martinez@correo.com');


insert into Habitacion (numero_habitacion, tipo_habitacion, disponibilidad, precio)
values 
    (101, 'Simple', 'Disponible', 50.00),
    (102, 'Doble', 'Ocupado', 75.00),
    (103, 'Familiar', 'Disponible', 100.00),
    (104, 'Suite', 'Disponible', 150.00),
    (106, 'Doble', 'Disponible', 75.00);
    
select * from Huesped;