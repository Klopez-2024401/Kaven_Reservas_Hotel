package org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "Usuario")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id_usuario;
    private String nombre_usuario;
    private String correo_usuario;
    private String contraseña;
}
