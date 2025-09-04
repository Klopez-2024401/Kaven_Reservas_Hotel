package org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.crud;

import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Huesped;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HuespedCrud extends JpaRepository <Huesped, Integer> {
}
