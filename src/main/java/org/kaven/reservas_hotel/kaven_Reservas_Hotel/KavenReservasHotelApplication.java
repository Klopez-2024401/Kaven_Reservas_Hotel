package org.kaven.reservas_hotel.kaven_Reservas_Hotel;

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
	private IUsuarioService usuarioService;
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
			    1. Listar todos los clientes.
			    2. Buscar cliente por código.
			    3. Agregar nuevo cliente.
			    4. Modificar cliente.
			    5. Eliminar cliente.
			    6. Salir.
			    Elija una opcion: \s""");
		var opcion = Integer.parseInt(consola.nextLine());
		return opcion;
	}
	private boolean ejecutarOpciones(Scanner consola, int opcion){
		var salir = false;
		switch (opcion){
			case 1 ->{
				logger.info(sl+"***Listado de todos los usuarios***"+sl);

			}
			case 2 ->{
				logger.info(sl+"Buscar usuario por su código"+sl);

			}
			case 3 ->{
				logger.info(sl+"***Agregar nuevo usuario***"+sl);

			}
			case 4 ->{
				logger.info(sl + "***Modificar Uusario ***" + sl);
				logger.info("Ingrese el codigo del usuario a editar");
			}
			case 5 ->{
				logger.info(sl+"*** Eliminar ususario ***"+sl);
			}
			case 6 ->{
				logger.info("Hasta pronto, Vaquero!"+sl+sl);
				salir = true;
			}
			default -> logger.info("Opcion no valida!!!");
		}
		return salir;
	}


}
