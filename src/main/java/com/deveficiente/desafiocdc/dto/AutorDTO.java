package com.deveficiente.desafiocdc.dto;

import com.deveficiente.desafiocdc.domain.entity.Autor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AutorDTO(
        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String descricao
        ) {

    public Autor toModel(){
        return new Autor(nome(), email(), descricao());
    }

    public AutorDTO(Autor autor){
        this(autor.getNome(), autor.getEmail(), autor.getDescricao());
    }
}
