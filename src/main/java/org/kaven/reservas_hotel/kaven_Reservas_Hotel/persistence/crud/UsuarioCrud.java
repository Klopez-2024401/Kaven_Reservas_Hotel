package org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.crud;

import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioCrud extends JpaRepository <Usuario, Integer>{
}
