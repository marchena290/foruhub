package com.alura.foruhub.domain.usuario;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario crear(Usuario usuario){
        return repository.save(usuario);
    }

    public List<Usuario> listar() {
        return repository.findAll();
    }

    @Transactional
    public Usuario actualizar(Usuario usuario) {
        return repository.save(usuario);
    }

    @Transactional
    public void eliminar(Long id) {
        repository.deleteById(id);
    }

}
