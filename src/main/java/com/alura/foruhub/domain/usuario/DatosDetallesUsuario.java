package com.alura.foruhub.domain.usuario;

public record DatosDetallesUsuario(
        Long id,
        String nombre,
        String email
) {
    public DatosDetallesUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNombre(), usuario.getEmail());
    }
}
