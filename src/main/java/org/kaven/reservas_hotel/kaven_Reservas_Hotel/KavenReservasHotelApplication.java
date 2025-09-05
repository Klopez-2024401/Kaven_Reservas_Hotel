package org.kaven.reservas_hotel.kaven_Reservas_Hotel;

import org.kaven.reservas_hotel.kaven_Reservas_Hotel.dominio.service.IHabitacionService;
import org.kaven.reservas_hotel.kaven_Reservas_Hotel.dominio.service.IHuespedService;
import org.kaven.reservas_hotel.kaven_Reservas_Hotel.dominio.service.IReservaService;
import org.kaven.reservas_hotel.kaven_Reservas_Hotel.dominio.service.IUsuarioService;
import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Habitacion;
import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Huesped;
import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Reserva;
import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

//@SpringBootApplication
public class KavenReservasHotelApplication implements CommandLineRunner {

    @Autowired
    private IHabitacionService habitacionService;
    @Autowired
    private IHuespedService huespedService;
    @Autowired
    private IReservaService reservaService;
    @Autowired
    private IUsuarioService usuarioService;

    private static final Logger logger = LoggerFactory.getLogger(KavenReservasHotelApplication.class);
    String sl = System.lineSeparator();

    public static void main(String[] args) {
        logger.info("Aqui inicia nuestra aplicacion");
        SpringApplication.run(KavenReservasHotelApplication.class, args);
        logger.info("Aqui termina la aplicacion");
    }

    @Override
    public void run(String... args) throws Exception {
        ReservaHotelApp();
    }

    private void ReservaHotelApp(){
        logger.info("++++++APLICACIÓN DE REGISTRAR HOTEL+++++");
        var salir = false;
        var consola = new Scanner(System.in);
        while(!salir){
            var opcion = mostrarMenu(consola);
            salir = ejecutarOpciones(consola, opcion);
            logger.info(sl);
        }
    }

    private int mostrarMenu(Scanner consola){
        logger.info("""
			    \n**Aplicación**
			    1. Gestión de Habitaciones
			    2. Gestión de Huéspedes
			    3. Gestión de Reservas
			    4. Gestión de Usuarios
			    5. Salir
			    Elija una opcion: \s""");
        var opcion = Integer.parseInt(consola.nextLine());
        return opcion;
    }

    private boolean ejecutarOpciones(Scanner consola, int opcion){
        var salir = false;
        switch (opcion){
            case 1 -> gestionHabitaciones(consola);
            case 2 -> gestionHuespedes(consola);
            case 3 -> gestionReservas(consola);
            case 4 -> gestionUsuarios(consola);
            case 5 -> {
                logger.info("Hasta pronto, Vaquero!"+sl+sl);
                salir = true;
            }
            default -> logger.info("Opcion no valida!!!");
        }
        return salir;
    }

    private void gestionHabitaciones(Scanner consola) {
        logger.info("""
                \n***Gestión de Habitaciones***
                1. Listar habitaciones
                2. Agregar habitación
                3. Editar habitación
                4. Eliminar habitación
                5. Volver al menú principal
                Elija una opción: \s""");
        var opcion = Integer.parseInt(consola.nextLine());

        switch (opcion) {
            case 1 -> {
                logger.info(sl + "***Listado de todas las habitaciones***" + sl);
                List<Habitacion> habitaciones = habitacionService.listarHabitaciones();
                habitaciones.forEach(habitacion -> logger.info(habitacion.toString() + sl));
            }
            case 2 -> {
                logger.info("Agregar nueva habitación:");
                System.out.print("Número de habitación: ");
                Integer numero = Integer.parseInt(consola.nextLine());

                String tipo = "";
                boolean tipoValido = false;
                while (!tipoValido) {
                    System.out.print("Tipo de habitación (Simple, Doble, Familiar, Suite): ");
                    tipo = consola.nextLine();
                    if (tipo.equals("Simple") || tipo.equals("Doble") || tipo.equals("Familiar") || tipo.equals("Suite")) {
                        tipoValido = true;
                    } else {
                        logger.info("¡Tipo de habitación no válido!");
                    }
                }

                String disponibilidad = "";
                boolean disponibilidadValida = false;
                while (!disponibilidadValida) {
                    System.out.print("Disponibilidad (Disponible, Ocupado): ");
                    disponibilidad = consola.nextLine();
                    if (disponibilidad.equals("Disponible") || disponibilidad.equals("Ocupado")) {
                        disponibilidadValida = true;
                    } else {
                        logger.info("¡Disponibilidad no válida!");
                    }
                }

                System.out.print("Precio: ");
                Double precio = Double.parseDouble(consola.nextLine());

                Habitacion habitacion = new Habitacion();
                habitacion.setNumero_habitacion(numero);
                habitacion.setTipo_habitacion(tipo);
                habitacion.setDisponibilidad(disponibilidad);
                habitacion.setPrecio(precio);

                habitacionService.guardarHabitacion(habitacion);
                logger.info("Habitación agregada exitosamente.");
            }
            case 3 -> {
                logger.info("Editar habitación:");
                System.out.print("ID de la habitación a editar: ");
                Integer id = Integer.parseInt(consola.nextLine());

                Habitacion habitacion = habitacionService.buscarHabitacionPorId(id);
                if (habitacion != null) {
                    System.out.print("Nuevo número de habitación (" + habitacion.getNumero_habitacion() + "): ");
                    String nuevoNumero = consola.nextLine();
                    if (!nuevoNumero.isEmpty()) {
                        habitacion.setNumero_habitacion(Integer.parseInt(nuevoNumero));
                    }

                    System.out.print("Nuevo tipo (Simple, Doble, Familiar, Suite) (" + habitacion.getTipo_habitacion() + "): ");
                    String nuevoTipo = consola.nextLine();
                    if (!nuevoTipo.isEmpty()) {
                        habitacion.setTipo_habitacion(nuevoTipo);
                    }

                    System.out.print("Nueva disponibilidad (Disponible, Ocupado) (" + habitacion.getDisponibilidad() + "): ");
                    String nuevaDisponibilidad = consola.nextLine();
                    if (!nuevaDisponibilidad.isEmpty()) {
                        habitacion.setDisponibilidad(nuevaDisponibilidad);
                    }

                    System.out.print("Nuevo precio (" + habitacion.getPrecio() + "): ");
                    String nuevoPrecio = consola.nextLine();
                    if (!nuevoPrecio.isEmpty()) {
                        habitacion.setPrecio(Double.parseDouble(nuevoPrecio));
                    }

                    habitacionService.guardarHabitacion(habitacion);
                    logger.info("Habitación modificada exitosamente.");
                } else {
                    logger.info("Habitación no encontrada.");
                }
            }
            case 4 -> {
                logger.info("Eliminar habitación:");
                System.out.print("ID de la habitación: ");
                Integer id = Integer.parseInt(consola.nextLine());

                Habitacion habitacion = habitacionService.buscarHabitacionPorId(id);
                if (habitacion != null) {
                    habitacionService.eliminarHabitacion(habitacion);
                    logger.info("Habitación eliminada.");
                } else {
                    logger.info("Habitación no encontrada.");
                }
            }
            case 5 -> logger.info("Volviendo al menú principal...");
            default -> logger.info("Opción no válida!!!");
        }
    }

    private void gestionHuespedes(Scanner consola) {
        logger.info("""
                \n***Gestión de Huéspedes***
                1. Listar huéspedes
                2. Agregar huésped
                3. Editar huésped
                4. Eliminar huésped
                5. Volver al menú principal
                Elija una opción: \s""");
        var opcion = Integer.parseInt(consola.nextLine());

        switch (opcion) {
            case 1 -> {
                logger.info(sl + "***Listado de todos los huéspedes***" + sl);
                List<Huesped> huespedes = huespedService.listarHuespedes();
                huespedes.forEach(huesped -> logger.info(huesped.toString() + sl));
            }
            case 2 -> {
                logger.info("Agregar nuevo huésped:");
                System.out.print("Nombre: ");
                String nombre = consola.nextLine();

                System.out.print("Apellido: ");
                String apellido = consola.nextLine();

                System.out.print("Correo: ");
                String correo = consola.nextLine();

                Huesped huesped = new Huesped();
                huesped.setNombre_huesped(nombre);
                huesped.setApellido_huesped(apellido);
                huesped.setCorreo_huesped(correo);

                huespedService.guardarHuesped(huesped);
                logger.info("Huésped agregado exitosamente.");
            }
            case 3 -> {
                logger.info("Editar huésped:");
                System.out.print("ID del huésped a editar: ");
                Integer id = Integer.parseInt(consola.nextLine());

                Huesped huesped = huespedService.buscarHuespedPorId(id);
                if (huesped != null) {
                    System.out.print("Nuevo nombre (" + huesped.getNombre_huesped() + "): ");
                    String nuevoNombre = consola.nextLine();
                    if (!nuevoNombre.isEmpty()) {
                        huesped.setNombre_huesped(nuevoNombre);
                    }

                    System.out.print("Nuevo apellido (" + huesped.getApellido_huesped() + "): ");
                    String nuevoApellido = consola.nextLine();
                    if (!nuevoApellido.isEmpty()) {
                        huesped.setApellido_huesped(nuevoApellido);
                    }

                    System.out.print("Nuevo correo (" + huesped.getCorreo_huesped() + "): ");
                    String nuevoCorreo = consola.nextLine();
                    if (!nuevoCorreo.isEmpty()) {
                        huesped.setCorreo_huesped(nuevoCorreo);
                    }

                    huespedService.guardarHuesped(huesped);
                    logger.info("Huésped modificado exitosamente.");
                } else {
                    logger.info("Huésped no encontrado.");
                }
            }
            case 4 -> {
                logger.info("Eliminar huésped:");
                System.out.print("ID del huésped: ");
                Integer id = Integer.parseInt(consola.nextLine());

                Huesped huesped = huespedService.buscarHuespedPorId(id);
                if (huesped != null) {
                    huespedService.eliminarHuesped(huesped);
                    logger.info("Huésped eliminado.");
                } else {
                    logger.info("Huésped no encontrado.");
                }
            }
            case 5 -> logger.info("Volviendo al menú principal...");
            default -> logger.info("Opción no válida!!!");
        }
    }

    private void gestionReservas(Scanner consola) {
        logger.info("""
                \n***Gestión de Reservas***
                1. Listar reservas
                2. Agregar reserva
                3. Editar reserva
                4. Eliminar reserva
                5. Volver al menú principal
                Elija una opción: \s""");
        var opcion = Integer.parseInt(consola.nextLine());

        switch (opcion) {
            case 1 -> {
                logger.info(sl + "***Listado de todas las reservas***" + sl);
                List<Reserva> reservas = reservaService.listarReservas();
                reservas.forEach(reserva -> logger.info(reserva.toString() + sl));
            }
            case 2 -> {
                logger.info("Agregar nueva reserva:");

                System.out.print("ID del huésped: ");
                Integer idHuesped = Integer.parseInt(consola.nextLine());

                System.out.print("ID de la habitación: ");
                Integer idHabitacion = Integer.parseInt(consola.nextLine());

                System.out.print("Fecha de entrada (yyyy-MM-dd): ");
                String fechaEntradaStr = consola.nextLine();

                System.out.print("Fecha de salida (yyyy-MM-dd): ");
                String fechaSalidaStr = consola.nextLine();

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaEntrada = sdf.parse(fechaEntradaStr);
                    Date fechaSalida = sdf.parse(fechaSalidaStr);

                    Reserva reserva = new Reserva();
                    reserva.setId_huesped(idHuesped);
                    reserva.setId_habitacion(idHabitacion);
                    reserva.setFecha_entrada(fechaEntrada);
                    reserva.setFecha_salida(fechaSalida);

                    reservaService.guardarReserva(reserva);
                    logger.info("Reserva agregada exitosamente.");
                } catch (Exception e) {
                    logger.info("Error en el formato de fecha. Use yyyy-MM-dd.");
                }
            }
            case 3 -> {
                logger.info("Editar reserva:");
                System.out.print("ID de la reserva a editar: ");
                Integer id = Integer.parseInt(consola.nextLine());

                Reserva reserva = reservaService.buscarReservasPorId(id);
                if (reserva != null) {
                    System.out.print("Nuevo ID de huésped (" + reserva.getId_huesped() + "): ");
                    String nuevoHuesped = consola.nextLine();
                    if (!nuevoHuesped.isEmpty()) {
                        reserva.setId_huesped(Integer.parseInt(nuevoHuesped));
                    }

                    System.out.print("Nuevo ID de habitación (" + reserva.getId_habitacion() + "): ");
                    String nuevaHabitacion = consola.nextLine();
                    if (!nuevaHabitacion.isEmpty()) {
                        reserva.setId_habitacion(Integer.parseInt(nuevaHabitacion));
                    }

                    System.out.print("Nueva fecha de entrada (yyyy-MM-dd) (" + reserva.getFecha_entrada() + "): ");
                    String nuevaEntrada = consola.nextLine();
                    if (!nuevaEntrada.isEmpty()) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date fechaEntrada = sdf.parse(nuevaEntrada);
                            reserva.setFecha_entrada(fechaEntrada);
                        } catch (Exception e) {
                            logger.info("Error en el formato de fecha.");
                        }
                    }

                    System.out.print("Nueva fecha de salida (yyyy-MM-dd) (" + reserva.getFecha_salida() + "): ");
                    String nuevaSalida = consola.nextLine();
                    if (!nuevaSalida.isEmpty()) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            Date fechaSalida = sdf.parse(nuevaSalida);
                            reserva.setFecha_salida(fechaSalida);
                        } catch (Exception e) {
                            logger.info("Error en el formato de fecha.");
                        }
                    }

                    reservaService.guardarReserva(reserva);
                    logger.info("Reserva modificada exitosamente.");
                } else {
                    logger.info("Reserva no encontrada.");
                }
            }
            case 4 -> {
                logger.info("Eliminar reserva:");
                System.out.print("ID de la reserva: ");
                Integer id = Integer.parseInt(consola.nextLine());

                Reserva reserva = reservaService.buscarReservasPorId(id);
                if (reserva != null) {
                    reservaService.eliminarReserva(reserva);
                    logger.info("Reserva eliminada.");
                } else {
                    logger.info("Reserva no encontrada.");
                }
            }
            case 5 -> logger.info("Volviendo al menú principal...");
            default -> logger.info("Opción no válida!!!");
        }
    }

    private void gestionUsuarios(Scanner consola) {
        logger.info("""
                \n***Gestión de Usuarios***
                1. Listar usuarios
                2. Agregar usuario
                3. Editar usuario
                4. Eliminar usuario
                5. Volver al menú principal
                Elija una opción: \s""");
        var opcion = Integer.parseInt(consola.nextLine());

        switch (opcion) {
            case 1 -> {
                logger.info(sl + "***Listado de todos los usuarios***" + sl);
                List<Usuario> usuarios = usuarioService.listarUsuarios();
                usuarios.forEach(usuario -> logger.info(usuario.toString() + sl));
            }
            case 2 -> {
                logger.info("Agregar nuevo usuario:");
                System.out.print("Nombre de usuario: ");
                String nombre = consola.nextLine();

                System.out.print("Correo: ");
                String correo = consola.nextLine();

                System.out.print("Contraseña: ");
                String contraseña = consola.nextLine();

                Usuario usuario = new Usuario();
                usuario.setNombre_usuario(nombre);
                usuario.setCorreo_usuario(correo);
                usuario.setContraseña(contraseña);

                usuarioService.guardarUsuario(usuario);
                logger.info("Usuario agregado exitosamente.");
            }
            case 3 -> {
                logger.info("Editar usuario:");
                System.out.print("ID del usuario a editar: ");
                Integer id = Integer.parseInt(consola.nextLine());

                Usuario usuario = usuarioService.buscarUsuarioPorId(id);
                if (usuario != null) {
                    System.out.print("Nuevo nombre de usuario (" + usuario.getNombre_usuario() + "): ");
                    String nuevoNombre = consola.nextLine();
                    if (!nuevoNombre.isEmpty()) {
                        usuario.setNombre_usuario(nuevoNombre);
                    }

                    System.out.print("Nuevo correo (" + usuario.getCorreo_usuario() + "): ");
                    String nuevoCorreo = consola.nextLine();
                    if (!nuevoCorreo.isEmpty()) {
                        usuario.setCorreo_usuario(nuevoCorreo);
                    }

                    System.out.print("Nueva contraseña: ");
                    String nuevaContraseña = consola.nextLine();
                    if (!nuevaContraseña.isEmpty()) {
                        usuario.setContraseña(nuevaContraseña);
                    }

                    usuarioService.guardarUsuario(usuario);
                    logger.info("Usuario modificado exitosamente.");
                } else {
                    logger.info("Usuario no encontrado.");
                }
            }
            case 4 -> {
                logger.info("Eliminar usuario:");
                System.out.print("ID del usuario: ");
                Integer id = Integer.parseInt(consola.nextLine());

                Usuario usuario = usuarioService.buscarUsuarioPorId(id);
                if (usuario != null) {
                    usuarioService.eliminarUsuario(usuario);
                    logger.info("Usuario eliminado.");
                } else {
                    logger.info("Usuario no encontrado.");
                }
            }
            case 5 -> logger.info("Volviendo al menú principal...");
            default -> logger.info("Opción no válida!!!");
        }
    }
}