package com.deveficiente.desafiocdc.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ErroSoComMensagemValidacao("recurso inexistente")
        );

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity tratarErro409(ConstraintViolationException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
            new ErroSoComMensagemValidacao(ex.getMessage()));
    }

    private record ErroSoComMensagemValidacao(String mensagem){

    }
}
