package org.kaven.reservas_hotel.kaven_Reservas_Hotel.service;

import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.crud.HuespedCrud;
import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Huesped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HuespedService implements IHuespedService{
    @Autowired
    private HuespedCrud crud;

    @Override
    public List<Huesped> listarHuespedes(){
        List<Huesped> habitaciones = crud.findAll();
        return habitaciones;
    }

    @Override
    public Huesped buscarHuespedPorId(Integer codigo){
        Huesped huesped = crud.findById(codigo).orElse(null);
        return huesped;
    }

    @Override
    public void guardarHuesped(Huesped huesped){
        crud.save(huesped);
    }

    @Override
    public void eliminarHuesped(Huesped huesped){
        crud.delete(huesped);
    }
}
