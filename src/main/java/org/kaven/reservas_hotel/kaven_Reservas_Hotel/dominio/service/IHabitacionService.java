package org.kaven.reservas_hotel.kaven_Reservas_Hotel.dominio.service;

import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Habitacion;

import java.util.List;

public interface IHabitacionService {
    List<Habitacion> listarHabitaciones();
    Habitacion buscarHabitacionPorId(Integer codigo);
    void guardarHabitacion(Habitacion habitacion);
    void eliminarHabitacion(Habitacion habitacion);
}
