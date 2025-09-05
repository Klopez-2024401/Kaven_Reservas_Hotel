package org.kaven.reservas_hotel.kaven_Reservas_Hotel.dominio.service;

import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Huesped;

import java.util.List;

public interface IHuespedService {
    List<Huesped> listarHuespedes();
    Huesped buscarHuespedPorId(Integer codigo);
    void guardarHuesped(Huesped huesped);
    void eliminarHuesped(Huesped huesped);

}
