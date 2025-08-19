package com.alura.foruhub.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizacionCurso(
        Long id,
        @NotBlank
        String nombre,
        String descripcion
) {
}
