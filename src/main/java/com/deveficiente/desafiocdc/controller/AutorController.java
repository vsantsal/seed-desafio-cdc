package com.deveficiente.desafiocdc.controller;

import com.deveficiente.desafiocdc.domain.repository.AutorRepository;
import com.deveficiente.desafiocdc.dto.AutorDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/cdc/autor")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<AutorDTO> salvar(
            @RequestBody @Valid AutorDTO dto,
            UriComponentsBuilder uriComponentsBuilder){
        var autor = dto.toModel();
        var autorSalvo = autorRepository.save(autor);
        var uri = uriComponentsBuilder.path("/api/cdc/autor/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).body(new AutorDTO(autorSalvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> detalhar(@PathVariable Long id) {
        var autor = autorRepository.getReferenceById(id);
        return ResponseEntity.ok(new AutorDTO(autor));
    }



}
