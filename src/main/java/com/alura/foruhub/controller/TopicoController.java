package com.alura.foruhub.controller;

import com.alura.foruhub.domain.ValidacionException;
import com.alura.foruhub.domain.topico.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private TopicoService service;

    // Crear tópico
    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetallesTopico> crear(
            @RequestBody @Valid DatosRegistroTopico datos,
            UriComponentsBuilder uriBuilder) {

        // Evitar duplicados
        if (repository.findByTituloAndMensaje(datos.titulo(), datos.mensaje()).isPresent()) {
            throw new ValidacionException("Tópico duplicado");
        }

        Topico topico = new Topico();
        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setCurso(datos.curso());
        topico.setAutor(datos.autor());
        topico.setStatus("Activo");
        topico.setFechaCreacion(java.time.LocalDateTime.now());

        service.crear(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetallesTopico(topico));
    }

    // Listar todos los tópicos
    @GetMapping
    public ResponseEntity<List<DatosDetallesTopico>> listar() {
        var topicos = repository.findAll()
                .stream()
                .map(DatosDetallesTopico::new)
                .toList();
        return ResponseEntity.ok(topicos);
    }

    // Obtener un tópico por id
    @GetMapping("/{id}")
    public ResponseEntity<DatosDetallesTopico> detallar(@PathVariable Long id) {
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new ValidacionException("Tópico no encontrado con id: " + id));
        return ResponseEntity.ok(new DatosDetallesTopico(topico));
    }

    // Actualizar un tópico
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosDetallesTopico> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizacionTopico datos) {

        Topico topico = repository.findById(id)
                .orElseThrow(() -> new ValidacionException("Tópico no encontrado con id: " + id));

        topico.setTitulo(datos.titulo());
        topico.setMensaje(datos.mensaje());
        topico.setCurso(datos.curso());
        topico.setAutor(datos.autor());

        repository.save(topico);

        return ResponseEntity.ok(new DatosDetallesTopico(topico));
    }

    // Eliminar un tópico
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        Topico topico = repository.findById(id)
                .orElseThrow(() -> new ValidacionException("Tópico no encontrado con id: " + id));

        repository.delete(topico);
        return ResponseEntity.noContent().build();
    }
}
