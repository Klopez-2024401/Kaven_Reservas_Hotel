package org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "Reserva")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id_reserva;
    private Date fecha_entrada;
    private Date fecha_salida;
    private Integer id_huesped;
    private Integer id_habitacion;
}
