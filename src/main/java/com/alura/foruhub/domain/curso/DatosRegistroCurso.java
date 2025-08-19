package com.alura.foruhub.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso (
        @NotBlank
        String nombre,
        String descripcion)
{
}
