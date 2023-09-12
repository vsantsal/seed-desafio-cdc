package com.deveficiente.desafiocdc.dto;

import com.deveficiente.desafiocdc.domain.entity.Autor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record AutorDTO(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
                @Size(max = 400)
        String descricao,

        LocalDateTime registro
        ) {

    public Autor toModel(){
        return new Autor(nome(), email(), descricao());
    }

    public AutorDTO(Autor autor){
        this(autor.getNome(), autor.getEmail(), autor.getDescricao(), autor.getCriadoEm());
    }
}
