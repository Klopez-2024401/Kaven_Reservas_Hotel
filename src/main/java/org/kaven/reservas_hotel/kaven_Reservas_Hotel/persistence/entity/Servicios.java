package org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity(name = "Servicios")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Servicios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id_servicio;
    private String nombre_servicio;
    private String descripcion;
    private Double precio_servicio;
}
