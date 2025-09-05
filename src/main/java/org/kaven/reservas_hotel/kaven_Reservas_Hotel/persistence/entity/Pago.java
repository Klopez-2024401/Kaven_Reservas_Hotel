package org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Entity(name = "Pago")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id_pago;
    private Double total;
    private String estado_pago;
    private String metodo_pago;
    private String id_reserva;
    private String id_usuario;

}
