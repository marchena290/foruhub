package com.alura.foruhub.controller;

import com.alura.foruhub.domain.usuario.DatosAutenticacion;
import com.alura.foruhub.domain.usuario.Usuario;
import com.alura.foruhub.infra.TokenService;
import com.alura.foruhub.infra.security.DatosTokenJWT;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity<DatosTokenJWT> iniciarSesion(@RequestBody @Valid DatosAutenticacion datos) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.email(), datos.password());
        var autenticacion = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generarToken((Usuario) autenticacion.getPrincipal());

        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}
