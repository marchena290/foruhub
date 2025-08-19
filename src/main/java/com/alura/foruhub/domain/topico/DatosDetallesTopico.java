package com.alura.foruhub.domain.topico;

import java.time.LocalDateTime;

public record DatosDetallesTopico(
        Long id,
        String titulo,
        String mensaje,
        String curso,
        String autor,
        LocalDateTime fechaCreacion
) {
    public DatosDetallesTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(),
                topico.getCurso(), topico.getAutor(), topico.getFechaCreacion());
    }
}
