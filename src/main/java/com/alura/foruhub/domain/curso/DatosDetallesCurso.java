package com.alura.foruhub.domain.curso;

public record DatosDetallesCurso (
    Long id,
    String nombre,
    String descripcion
)
{
    public DatosDetallesCurso(Curso curso) {
        this(curso.getId(), curso.getNombre(), curso.getDescripcion());
    }
}
