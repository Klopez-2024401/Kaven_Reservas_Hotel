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
    tipo_habitacion enum('Simple', 'Doble', 'Familiar', 'Suite') default 'Simple',
    disponibilidad enum('Disponible', 'Ocupado') default 'Disponible',
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
		references Huesped (id_huesped)
    on delete cascade,    
	constraint fk_habitacion_reserva foreign key (id_habitacion)
		references Habitacion (id_habitacion)
	on delete cascade
);

create table Pago (
	id_pago int not null auto_increment,
    total decimal(10, 2) not null,
    estado_pago ENUM('Pendiente','Pagado') default 'Pendiente',
    metodo_pago ENUM('Tarjeta de credito','Efectivo','Transferencia movil') default 'Tarjeta de credito',
    id_reserva int not null,
    id_usuario int not null,
    constraint pk_pago primary key (id_pago),
    constraint fk_reserva_pago foreign key (id_reserva)
		references Reserva (id_reserva)
    on delete cascade,    
	constraint fk_usuario_pago foreign key (id_usuario)
		references Usuario (id_usuario)
	on delete cascade
);

insert into Usuario (nombre_usuario, correo_usuario, contraseña)
 values
('admin', 'admin@kaven.com', 'admin123'),
('jperez', 'jperez@email.com', 'pass1234'),
('mgarcia', 'mgarcia@email.com', 'securepass'),
('lrodriguez', 'lrodriguez@email.com', 'mypassword'),
('cmartinez', 'cmartinez@email.com', 'clave123'),
('asanchez', 'asanchez@email.com', 'sancheza'),
('rlopez', 'rlopez@email.com', 'lopezr'),
('agomez', 'agomez@email.com', 'gomeza'),
('tfernandez', 'tfernandez@email.com', 'fernandezt'),
('druiz', 'druiz@email.com', 'ruizd');

insert into Huesped (nombre_huesped, apellido_huesped, correo_huesped) 
values
('Juan', 'Pérez', 'juan.perez@email.com'),
('María', 'González', 'maria.gonzalez@email.com'),
('Carlos', 'López', 'carlos.lopez@email.com'),
('Ana', 'Martínez', 'ana.martinez@email.com'),
('Luis', 'Rodríguez', 'luis.rodriguez@email.com'),
('Sofía', 'Hernández', 'sofia.hernandez@email.com'),
('Miguel', 'Díaz', 'miguel.diaz@email.com'),
('Elena', 'Moreno', 'elena.moreno@email.com'),
('Pedro', 'Álvarez', 'pedro.alvarez@email.com'),
('Laura', 'Romero', 'laura.romero@email.com');

insert into Habitacion (numero_habitacion, tipo_habitacion, disponibilidad, precio) 
values
(101, 'Simple', 'Disponible', 50.00),
(102, 'Simple', 'Disponible', 50.00),
(103, 'Doble', 'Disponible', 80.00),
(104, 'Doble', 'Disponible', 80.00),
(105, 'Familiar', 'Disponible', 120.00),
(106, 'Familiar', 'Disponible', 120.00),
(107, 'Suite', 'Disponible', 200.00),
(108, 'Suite', 'Disponible', 200.00),
(201, 'Simple', 'Disponible', 55.00),
(202, 'Doble', 'Disponible', 85.00);

insert into Reserva (fecha_entrada, fecha_salida, id_huesped, id_habitacion) 
values
('2024-01-15', '2024-01-20', 1, 1),
('2024-01-16', '2024-01-18', 2, 2),
('2024-01-17', '2024-01-22', 3, 3),
('2024-01-18', '2024-01-21', 4, 4),
('2024-01-19', '2024-01-25', 5, 5),
('2024-01-20', '2024-01-23', 6, 6),
('2024-01-21', '2024-01-24', 7, 7),
('2024-01-22', '2024-01-26', 8, 8),
('2024-01-23', '2024-01-27', 9, 9),
('2024-01-24', '2024-01-28', 10, 10);

insert into Pago (estado_pago, id_reserva, id_usuario) 
values
('Pagado', 1, 1),
('Pagado', 2, 2),
('Pagado', 3, 3),
('Pendiente', 4, 4),
('Pagado', 5, 5),
('Pendiente', 6, 6),
('Pagado', 7, 7),
('Pagado', 8, 8),
('Pendiente', 9, 9),
('Pagado', 10, 10);

