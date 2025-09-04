package org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.crud;

import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitacionCrud extends JpaRepository <Habitacion, Integer>{

}
