package org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.crud;

import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservaCrud extends JpaRepository <Reserva, Integer>{
}
