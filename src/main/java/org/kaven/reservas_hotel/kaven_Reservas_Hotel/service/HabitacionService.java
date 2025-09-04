package org.kaven.reservas_hotel.kaven_Reservas_Hotel.service;

import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.crud.HabitacionCrud;
import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Habitacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HabitacionService implements IHabitacionService{
    @Autowired
    private HabitacionCrud crud;

    @Override
    public List<Habitacion> listarHabitaciones(){
        List<Habitacion> habitaciones = crud.findAll();
        return habitaciones;
    }

    @Override
    public Habitacion buscarHabitacionPorId(Integer codigo){
        Habitacion habitacion = crud.findById(codigo).orElse(null);
        return habitacion;
    }

    @Override
    public void guardarHabitacion(Habitacion habitacion){
        crud.save(habitacion);
    }

    @Override
    public void eliminarHabitacion(Habitacion habitacion){
        crud.delete(habitacion);
    }
}
