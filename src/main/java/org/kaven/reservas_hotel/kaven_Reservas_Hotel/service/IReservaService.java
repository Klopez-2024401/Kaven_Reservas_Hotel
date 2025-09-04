package org.kaven.reservas_hotel.kaven_Reservas_Hotel.service;

import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Reserva;

import java.util.List;

public interface IReservaService {
    List<Reserva> listarReservas();
    Reserva buscarReservasPorId(Integer codigo);
    void guardarReserva(Reserva reserva);
    void eliminarReserva(Reserva reserva);
}
