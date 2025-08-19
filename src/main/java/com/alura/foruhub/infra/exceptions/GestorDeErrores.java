package com.alura.foruhub.infra.exceptions;

import com.alura.foruhub.domain.ValidacionException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GestorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity gestionarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity gestionarErro400(MethodArgumentNotValidException ex){
        var errores = ex.getFieldErrors();
        return ResponseEntity.badRequest()
                .body(errores.stream().map(DatosErrorValidacion::new).toList());
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity gestionarErro400(ValidacionException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    public record DatosErrorValidacion(String campo, String mensaje) {
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

    @ExceptionHandler(org.springframework.dao.DataIntegrityViolationException.class)
    public ResponseEntity<String> manejarDataIntegrity(org.springframework.dao.DataIntegrityViolationException ex){
        return ResponseEntity.badRequest().body("No se puede actualizar el tópico: algún campo obligatorio está vacío");
    }

}
