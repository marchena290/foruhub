package com.alura.foruhub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record DatosActualizacionTopico(
        Long id,
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String curso,
        @NotBlank String autor
) {
}
