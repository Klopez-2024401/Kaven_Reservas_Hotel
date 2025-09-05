package org.kaven.reservas_hotel.kaven_Reservas_Hotel.dominio.service;

import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.crud.ReservaCrud;
import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Reserva;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaService implements IReservaService{
    @Autowired
    private ReservaCrud crud;

    @Override
    public List<Reserva> listarReservas(){
        List<Reserva> reservas = crud.findAll();
        return reservas;
    }

    @Override
    public Reserva buscarReservasPorId(Integer codigo){
        Reserva reserva = crud.findById(codigo).orElse(null);
        return reserva;
    }

    @Override
    public void guardarReserva(Reserva reserva){
        crud.save(reserva);
    }

    @Override
    public void eliminarReserva(Reserva reserva){
        crud.delete(reserva);
    }
}

