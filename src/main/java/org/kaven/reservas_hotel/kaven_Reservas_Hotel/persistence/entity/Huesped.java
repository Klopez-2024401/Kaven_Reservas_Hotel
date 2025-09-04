package org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity;

import jakarta.persistence.*;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Huesped")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Huesped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id_huesped;
    private String nombre_huesped;
    private String apellido_huesped;
    private String correo_huesped;
}
