package org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Habitacion")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Habitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id_habitacion;
    private Integer numero_habitacion;
    private String tipo_habitacion;
    private String disponibilidad;
    private Double precio;
}
