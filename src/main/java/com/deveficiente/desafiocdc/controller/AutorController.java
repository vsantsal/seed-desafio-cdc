package com.deveficiente.desafiocdc.controller;


import com.deveficiente.desafiocdc.domain.entity.Autor;
import com.deveficiente.desafiocdc.dto.AutorDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/cdc/autor")
public class AutorController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<AutorDTO> salvar(
            @RequestBody @Valid AutorDTO dto,
            UriComponentsBuilder uriComponentsBuilder){
        var autor = dto.toModel();
        manager.persist(autor);
        var uri = uriComponentsBuilder.path("/api/cdc/autor/{id}").buildAndExpand(autor.getId()).toUri();
        return ResponseEntity.created(uri).body(new AutorDTO(autor));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> detalhar(@PathVariable Long id) {
        var autor = manager.getReference(Autor.class, 1L);
        return ResponseEntity.ok(new AutorDTO(autor));
    }



}
