package com.deveficiente.desafiocdc.controller;

import com.deveficiente.desafiocdc.domain.repository.AutorRepository;
import com.deveficiente.desafiocdc.dto.AutorDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cdc/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    public ResponseEntity<AutorDTO> salvar(@RequestBody @Valid AutorDTO dto){
        var autor = dto.toModel();
        var autorSalvo = autorRepository.save(autor);
        return ResponseEntity.ok(new AutorDTO(autorSalvo));
    }


}
