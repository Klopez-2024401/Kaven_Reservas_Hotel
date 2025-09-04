package org.kaven.reservas_hotel.kaven_Reservas_Hotel;

import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Habitacion;
import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Huesped;
import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Reserva;
import org.kaven.reservas_hotel.kaven_Reservas_Hotel.service.IHabitacionService;
import org.kaven.reservas_hotel.kaven_Reservas_Hotel.service.IHuespedService;
import org.kaven.reservas_hotel.kaven_Reservas_Hotel.service.IReservaService;
import org.kaven.reservas_hotel.kaven_Reservas_Hotel.service.IUsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class KavenReservasHotelApplication implements CommandLineRunner {

	@Autowired
	private IHabitacionService habitacionService;
	@Autowired
	private IHuespedService huespedService;
	@Autowired
	private IReservaService reservaService;
	private static final Logger logger = LoggerFactory.getLogger(KavenReservasHotelApplication.class);
	String sl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Aui inicia nuestra aplicacion");
		SpringApplication.run(KavenReservasHotelApplication.class, args);
		logger.info("Aui termina la aplicacion");
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
			    \n***Aplicación***
			    1. Agregar habitaciones disponibles.
			    2. Buscar Habitacion por ID.
			    3. Usuario elegir habitacion.
			    4. Modificar usuario.
			    5. Eliminar Habitacion.
			    6. Habitaciones reservadas.
			    7. Lista de huéspedes y las habitaciones que ocupan
			    8. Salir.
			    Elija una opcion: \s""");
		var opcion = Integer.parseInt(consola.nextLine());
		return opcion;
	}
	private boolean ejecutarOpciones(Scanner consola, int opcion){
		var salir = false;
		switch (opcion){
			case 1 -> {
				logger.info("Agregar nueva habitación:");

				// Obtener número de habitación
				System.out.print("Número de habitación: ");
				Integer numero = Integer.parseInt(consola.nextLine());

				// Obtener tipo de habitación
				String tipo = "";
				boolean tipoValido = false;
				while (!tipoValido) {
					System.out.print("Tipo de habitación (Simple, Doble, Familiar, Suite): ");
					tipo = consola.nextLine();
					if (tipo.equals("Simple") || tipo.equals("Doble") || tipo.equals("Familiar") || tipo.equals("Suite")) {
						tipoValido = true;
					} else {
						logger.info("¡Tipo de habitación no válido! Por favor, ingrese uno de los siguientes: 'Simple', 'Doble', 'Familiar', 'Suite'.");
					}
				}

				// Obtener disponibilidad
				String disponibilidad = "";
				boolean disponibilidadValida = false;
				while (!disponibilidadValida) {
					System.out.print("Disponibilidad (Disponible, Ocupado): ");
					disponibilidad = consola.nextLine();
					if (disponibilidad.equals("Disponible") || disponibilidad.equals("Ocupado")) {
						disponibilidadValida = true;
					} else {
						logger.info("¡Disponibilidad no válida! Por favor, ingrese 'Disponible' o 'Ocupado'.");
					}
				}

				// Obtener precio
				System.out.print("Precio: ");
				Double precio = Double.parseDouble(consola.nextLine());

				// Crear y guardar la nueva habitación
				Habitacion habitacion = new Habitacion();
				habitacion.setNumero_habitacion(numero);
				habitacion.setTipo_habitacion(tipo); // Asignar tipo de habitación válido
				habitacion.setDisponibilidad(disponibilidad); // Asignar disponibilidad válida
				habitacion.setPrecio(precio);

				habitacionService.guardarHabitacion(habitacion);
				logger.info("Habitación agregada exitosamente.");
			}
			case 2 -> {
				logger.info("Buscar habitación por ID:");
				System.out.print("Ingrese el ID: ");
				Integer id = Integer.parseInt(consola.nextLine());

				Habitacion habitacion = habitacionService.buscarHabitacionPorId(id);
				if (habitacion != null) {
					logger.info("Habitación encontrada: " + habitacion);
				} else {
					logger.info("No se encontró ninguna habitación con ese ID.");
				}
			}
			case 3 -> {
				logger.info("Usuario elegir habitación:");

				// Listar todas las habitaciones disponibles
				List<Habitacion> habitaciones = habitacionService.listarHabitaciones();
				boolean habitacionesDisponibles = false;

				// Muestra todas las habitaciones disponibles
				for (Habitacion habitacion : habitaciones) {
					if (habitacion.getDisponibilidad().equals("Disponible")) {
						habitacionesDisponibles = true;
						logger.info("ID: " + habitacion.getId_habitacion() +
								", Número: " + habitacion.getNumero_habitacion() +
								", Tipo: " + habitacion.getTipo_habitacion() +
								", Precio: " + habitacion.getPrecio() +
								", Disponibilidad: " + habitacion.getDisponibilidad());
					}
				}

				if (!habitacionesDisponibles) {
					logger.info("No hay habitaciones disponibles en este momento.");
					break; // Salir si no hay habitaciones disponibles
				}

				// Solicitar al usuario que elija una habitación por su ID
				System.out.print("Por favor, ingrese el ID de la habitación que desea reservar: ");
				Integer idHabitacion = Integer.parseInt(consola.nextLine());

				// Buscar la habitación seleccionada
				Habitacion habitacionSeleccionada = habitacionService.buscarHabitacionPorId(idHabitacion);

				// Si no existe la habitación o está ocupada, informar al usuario
				if (habitacionSeleccionada == null || habitacionSeleccionada.getDisponibilidad().equals("Ocupado")) {
					logger.info("Habitación no disponible o no encontrada.");
					break; // Salir si la habitación no está disponible
				}

				// Solicitar los datos del huésped
				System.out.print("Ingrese el nombre del huésped: ");
				String nombreHuesped = consola.nextLine();

				System.out.print("Ingrese el apellido del huésped: ");
				String apellidoHuesped = consola.nextLine(); // Pedir el apellido

				System.out.print("Ingrese el correo del huésped: ");
				String correoHuesped = consola.nextLine();

				// Crear el objeto Huesped (asumiendo que tienes la clase Huesped configurada)
				Huesped huesped = new Huesped();
				huesped.setNombre_huesped(nombreHuesped);
				huesped.setApellido_huesped(apellidoHuesped); // Asignar el apellido
				huesped.setCorreo_huesped(correoHuesped);

				// Guardar el huésped
				huespedService.guardarHuesped(huesped);

				// Actualizar la habitación a "Ocupado"
				habitacionSeleccionada.setDisponibilidad("Ocupado");
				habitacionService.guardarHabitacion(habitacionSeleccionada); // Guardar la habitación con el estado actualizado

				logger.info("Reserva confirmada! La habitación " + habitacionSeleccionada.getNumero_habitacion() +
						" ha sido ocupada por " + nombreHuesped + " " + apellidoHuesped + ".");
			}
			case 4 ->{
				logger.info("Modificar huésped:");
				System.out.print("ID del huésped: ");
				Integer id = Integer.parseInt(consola.nextLine());
				Huesped huesped = huespedService.buscarHuespedPorId(id);
				if (huesped != null) {
					System.out.print("Nuevo nombre: ");
					String nuevoNombre = consola.nextLine();
					huesped.setNombre_huesped(nuevoNombre);

					huespedService.guardarHuesped(huesped);
					logger.info("Huésped modificado exitosamente.");
				} else {
					logger.info("Huésped no encontrado.");
				}
			}
			case 5 ->{
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
			case 6 ->{
				logger.info("Habitaciones reservadas:");

				// Obtener todas las reservas
				List<Reserva> reservas = reservaService.listarReservas();
				boolean reservasExistentes = false;

				for (Reserva reserva : reservas) {
					// Buscar la habitación correspondiente a la reserva
					Habitacion habitacion = habitacionService.buscarHabitacionPorId(reserva.getId_habitacion());

					// Verificar si la habitación está ocupada
					if (habitacion != null && habitacion.getDisponibilidad().equals("Ocupado")) {
						reservasExistentes = true; // Si encontramos alguna habitación ocupada, cambiamos el flag

						// Mostrar los detalles de la reserva y la habitación asociada
						logger.info("Reserva ID: " + reserva.getId_reserva());
						logger.info("Habitación ID: " + habitacion.getId_habitacion());
						logger.info("Número: " + habitacion.getNumero_habitacion());
						logger.info("Tipo: " + habitacion.getTipo_habitacion());
						logger.info("Precio: " + habitacion.getPrecio());
						logger.info("Huésped: " + huespedService.buscarHuespedPorId(reserva.getId_huesped()).getNombre_huesped());
						logger.info("Fecha entrada: " + reserva.getFecha_entrada());
						logger.info("Fecha salida: " + reserva.getFecha_salida());
						logger.info("Disponibilidad: " + habitacion.getDisponibilidad());
						logger.info("-------------------------------------");
					}
				}

				// Si no se encuentran reservas, mostrar un mensaje
				if (!reservasExistentes) {
					logger.info("No hay habitaciones reservadas o ocupadas en este momento.");
				}
			}
			case 7 -> {
				logger.info("Lista de huéspedes y las habitaciones que ocupan:");

				// Obtener todos los huéspedes
				List<Huesped> huespedes = huespedService.listarHuespedes();

				if (huespedes.isEmpty()) {
					logger.info("No hay huéspedes registrados.");
				} else {
					for (Huesped huesped : huespedes) {
						// Buscar las reservas asociadas a cada huésped
						List<Reserva> reservas = reservaService.listarReservas(); // O puede ser un servicio de búsqueda más específico para reservas de un huésped
						for (Reserva reserva : reservas) {
							if (reserva.getId_huesped().equals(huesped.getId_huesped())) {
								// Buscar la habitación asociada a la reserva
								Habitacion habitacion = habitacionService.buscarHabitacionPorId(reserva.getId_habitacion());
								if (habitacion != null) {
									// Mostrar el nombre del huésped y el ID de la habitación
									logger.info("Huésped: " + huesped.getNombre_huesped() + " " + huesped.getApellido_huesped());
									logger.info("Habitación ID: " + habitacion.getId_habitacion() + ", Número: " + habitacion.getNumero_habitacion());
								}
							}
						}
					}
				}
			}
			case 8 ->{
				logger.info("Hasta pronto, Vaquero!"+sl+sl);
				salir = true;
			}
			default -> logger.info("Opcion no valida!!!");
		}
		return salir;
	}


}
