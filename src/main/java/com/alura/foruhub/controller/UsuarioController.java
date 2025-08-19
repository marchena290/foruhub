package com.alura.foruhub.controller;

import com.alura.foruhub.domain.usuario.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Crear usuario
    @PostMapping
    @Transactional
    public ResponseEntity<DatosDetallesUsuario> crear(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriBuilder) {
        var usuario = new Usuario();
        usuario.setNombre(datos.nombre());
        usuario.setEmail(datos.email());
        usuario.setPassword(passwordEncoder.encode(datos.password())); // Encriptamos la contraseña

        service.crear(usuario);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetallesUsuario(usuario));
    }

    // Listar usuarios
    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    // Detallar un usuario
    @GetMapping("/{id}")
    public ResponseEntity<DatosDetallesUsuario> detallar(@PathVariable Long id) {
        var usuario = service.listar().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));
        return ResponseEntity.ok(new DatosDetallesUsuario(usuario));
    }

    // Actualizar usuario
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosDetallesUsuario> actualizar(@PathVariable Long id, @RequestBody @Valid DatosActualizacionUsuario datos) {
        var usuario = service.listar().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        usuario.setNombre(datos.nombre());
        usuario.setEmail(datos.email());

        service.actualizar(usuario);
        return ResponseEntity.ok(new DatosDetallesUsuario(usuario));
    }

    // Eliminar usuario
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
