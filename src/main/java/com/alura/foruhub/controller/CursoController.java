package com.alura.foruhub.controller;

import com.alura.foruhub.domain.curso.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetallesCurso> crear(@RequestBody @Valid DatosRegistroCurso datos, UriComponentsBuilder uriBuilder) {
        var curso = new Curso(datos.nombre(), datos.descripcion());
        repository.save(curso);

        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetallesCurso(curso));
    }

    @GetMapping
    public ResponseEntity<List<DatosDetallesCurso>> listar() {
        var lista = repository.findAll().stream().map(DatosDetallesCurso::new).toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetallesCurso> detallar(@PathVariable Long id) {
        var curso = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con id: " + id));
        return ResponseEntity.ok(new DatosDetallesCurso(curso));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosDetallesCurso> actualizar(@RequestBody @Valid DatosActualizacionCurso datos) {
        var curso = repository.findById(datos.id())
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con id: " + datos.id()));

        curso.setNombre(datos.nombre());
        curso.setDescripcion(datos.descripcion());
        repository.save(curso);

        return ResponseEntity.ok(new DatosDetallesCurso(curso));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        var curso = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado con id: " + id));
        repository.delete(curso);
        return ResponseEntity.noContent().build();
    }

}

