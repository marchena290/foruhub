package com.alura.foruhub.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizacionUsuario(
        @NotBlank
        String nombre,
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
