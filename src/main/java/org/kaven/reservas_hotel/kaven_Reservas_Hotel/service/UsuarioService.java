package org.kaven.reservas_hotel.kaven_Reservas_Hotel.service;

import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.crud.UsuarioCrud;
import org.kaven.reservas_hotel.kaven_Reservas_Hotel.persistence.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements IUsuarioService{
    @Autowired
    private UsuarioCrud crud;

    @Override
    public List<Usuario> listarUsuarios(){
        List<Usuario> usuarios = crud.findAll();
        return usuarios;
    }

    @Override
    public Usuario buscarUsuarioPorId(Integer codigo){
        Usuario usuario = crud.findById(codigo).orElse(null);
        return usuario;
    }

    @Override
    public void guardarUsuario(Usuario usuario){
        crud.save(usuario);
    }

    @Override
    public void eliminarUsuario(Usuario usuario){
        crud.delete(usuario);
    }
}
