package org.kaven.reservas_hotel.kaven_Reservas_Hotel.dominio.service;

import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    List<Usuario> listarUsuarios();
    Usuario buscarUsuarioPorId(Integer codigo);
    void guardarUsuario(Usuario usuario);
    void eliminarUsuario(Usuario usuario);
}
